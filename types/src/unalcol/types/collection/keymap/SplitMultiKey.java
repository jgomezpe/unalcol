package unalcol.types.collection.keymap;

public class SplitMultiKey<T> implements MultiKey<T>{
	protected Key<String,T> key;
	public SplitMultiKey( Key<String, T> key ){ this.key = key; }
	public String[] keys( T obj ){ return key.key(obj).split(","); }
}
