package unalcol.types.object;

import unalcol.types.collection.keymap.Key;

public class ObjectKey implements Key<String, Object>{
	@Override
	public String key(Object obj){ return obj.toString(); }
}