package unalcol.json;

import java.util.Hashtable;

public class Factory<T> implements JSON2Instance<T>{
	public static final String TYPE = "type";
	
	protected Hashtable<String, JSON2Instance<T>> map = new Hashtable<String,JSON2Instance<T>>();
	protected Hashtable<String, String> alias = new Hashtable<String,String>();
	protected String defaultTag=null;
	
	public void register( String tag, String class_name, JSON2Instance<T> instance ){
		map.put(tag, instance);
		this.alias.put(class_name, tag);
		defaultTag = tag;
	}
	
	public void clear(){ 
		map.clear();
		alias.clear();
	}
	
	public T load(JSON json ){
		String type = defaultTag;
		try{ type = (String)json.get(TYPE); }catch(Exception e ){}
		try{ 
			JSON2Instance<T> instance = map.get(type);
			return instance.load(json);
		}catch(Exception e){ return null; }
	}
	
	public JSON store(T obj){
		String tag = alias.get(obj.getClass().getName());
		JSON2Instance<T> instance = map.get(tag);
		if( instance != null ){
			JSON json = instance.store(obj);
			if(alias.size()>1) json.set(TYPE, tag);
			return json;
		}
		return null;
	}
}