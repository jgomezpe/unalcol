package unalcol.reflect.tag;

import java.util.Hashtable;
import java.util.Set;

import unalcol.clone.Clone;

public class TaggedObject<T>{
	protected T object;
	
	protected Hashtable<String, Object> info = new Hashtable<>();
	
	public TaggedObject( T object ){ this.object = object; }

	public TaggedObject<T> instance(T object ){
		return new TaggedObject<T>(object); 
	}
	
	public TaggedObject<T> clone( T object ){
		TaggedObject<T> cl = instance(object);
		cl.cloneTaggedMethods(this);
		return cl;
	}
	
	public void cloneTags( TaggedObject<T> x ){
		Set<String> keys = x.info.keySet();
		for(String k:keys){
			Object obj = x.data(k);
			info.put(k, obj);
		}
	}
	
	public void cloneTaggedMethods( TaggedObject<T> x ){
		Set<String> keys = x.info.keySet();
		for(String k:keys){
			Object obj = x.data(k);
			if( obj instanceof TaggedMethod ){
				info.put(k, obj);
			}
		}
	}
	
	public TaggedObject<T> clone(){
		@SuppressWarnings("unchecked")
		T x = (T)Clone.create(object);
		TaggedObject<T> cl = new TaggedObject<T>(x);
		Set<String> keys = info.keySet();
		for(String k:keys){
			Object obj = data(k);
			cl.set(k, obj);
		}
		return cl;
	}
	
	public void set( T object ){
		this.object = object;
		Set<String> keys = info.keySet();
		for(String k:keys){
			Object obj = data(k);
			if( !(obj instanceof TaggedMethod) ){
				info.remove(k);
			}
		}		
	}
	
	public T object(){ return object; }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object info( String key ){
		Object obj = info.get(key);
		if( obj instanceof TaggedMethod ){
			try{
				return ((TaggedMethod)obj).apply(this);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		} 
		return obj;
	}
	
	public Object data( String key ){
		return info.get(key);
	}
	
	public void set( String key, Object value ){
		info.put(key, value);
	}	
}