package unalcol.plugin;

import unalcol.collection.Array;
import unalcol.collection.Vector;
import unalcol.xml.XMLElement;

public interface PlugInTree extends PlugIn{
	@Override
	default void init(XMLElement element, PlugInSet<?> builder){
		setId(PlugIn.id(element));
		Vector<Object> children = new Vector<Object>();
		for( Object e:element.children()){
			XMLElement elem = (XMLElement)e;
			try{
				PlugIn p = (PlugIn)builder.get(elem);
				children.add(p);
			}catch(Exception ex){}
		}
		setChildren(children);
	}
	
	public void setChildren(Array<Object> children);
}