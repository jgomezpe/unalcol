package unalcol.reflect.plugin;

import unalcol.types.collection.keymap.Key;

public class PlugInKey<T> implements Key<String,T> {
	@Override
	public String get(T obj){ return (obj instanceof PlugIn)?((PlugIn)obj).id():obj.toString(); }
}