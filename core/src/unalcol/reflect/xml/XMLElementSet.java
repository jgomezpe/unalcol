package unalcol.reflect.xml;

import unalcol.types.collection.keymap.MultiIdCollection;

public class XMLElementSet extends MultiIdCollection<XMLElement>{
	public XMLElementSet( String key ){ super( new XMLElementKey(key) ); }
}