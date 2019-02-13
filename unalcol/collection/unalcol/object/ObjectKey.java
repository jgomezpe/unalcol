package unalcol.object;

import unalcol.collection.keymap.Key;

public class ObjectKey implements Key<String, Object>{
	@Override
	public String key(Object obj){ return obj.toString(); }
}