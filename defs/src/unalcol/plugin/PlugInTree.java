package unalcol.plugin;

import unalcol.types.collection.array.Array;
import unalcol.types.collection.vector.Vector;
import unalcol.types.object.Thing;
import unalcol.xml.XMLElement;

public interface PlugInTree extends Thing, PlugIn{
	@Override
	default void init(XMLElement element, PlugInSet<?> builder){
		setId(PlugIn.id(element));
		Vector<Object> children = new Vector<Object>();
		for( Object e:element.children()){
			XMLElement elem = (XMLElement)e;
			PlugIn p = (PlugIn)builder.get(elem);
			if( p!=null ) children.add(p);
		}
		setChildren(children);
	}
	
	public void setChildren(Array<Object> children);
}