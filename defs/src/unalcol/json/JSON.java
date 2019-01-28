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
}