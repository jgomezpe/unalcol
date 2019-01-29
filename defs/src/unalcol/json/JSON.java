package unalcol.json;

import unalcol.clone.Cloneable;
import unalcol.types.collection.keymap.LowLevelKeyMap;

public class JSON extends LowLevelKeyMap<String, Object> implements Cloneable{
	@Override
	public Object clone(){
		JSON json = new JSON();
		for( String key:keys() ){
			Object obj = get(key);
			Cloneable c = Cloneable.cast(obj);
			json.set(key, c.clone());
		}
		return json;
	}
	
	public int getInt( String tag ){
		Integer i = (Integer)get(tag);
		if( i==null ) return 0;
		return i;
	}
	
	public boolean getBool( String tag ){
		Boolean i = (Boolean)get(tag);
		if( i==null ) return false;
		return i;
	}
	
}