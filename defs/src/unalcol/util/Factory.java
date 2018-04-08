package unalcol.util;

import java.util.Hashtable;

public class Factory<T> {
	protected Hashtable<String, Instance<T>> map = new Hashtable<String,Instance<T>>();
	protected Hashtable<String, String> alias = new Hashtable<String,String>();
	
	public void register( String tag, String class_name, Instance<T> instance ){
		map.put(tag, instance);
		this.alias.put(class_name, tag);
	}
	
	public void clear(){ 
		map.clear();
		alias.clear();
	}
	
	public T load(Object[] args ){
		if( args.length==0 || !(args[0] instanceof String) ) return null;
		Instance<T> instance = map.get((String)args[0]);
		if( instance != null ) return instance.load(args);
		return null;
	}
	
	public Object[] store(T obj){
		String tag = alias.get(obj.getClass().getName());
		Instance<T> instance = map.get(tag);
		if( instance != null ) return instance.store(obj);
		return null;
	}
}