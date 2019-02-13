package unalcol.collection.keymap;

import unalcol.collection.keymap.Key;

public class SplitMultiKey<T> implements MultiKey<T>{
	protected Key<String,T> key;
	public SplitMultiKey( Key<String, T> key ){ this.key = key; }
	public String[] keys( T obj ){ return key.key(obj).split(","); }
}
