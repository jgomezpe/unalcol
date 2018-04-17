package unalcol.reflect.plugin;

import unalcol.reflect.xml.XMLElement;
import unalcol.types.collection.Collection;
import unalcol.types.collection.vector.Vector;

public class PlugInTree implements PlugIn{
	protected Vector<PlugIn> children = new Vector<PlugIn>();
	protected String id;
	
	public PlugInTree(){ this.id = null; }

	@Override
	public void init(XMLElement element, PlugInSet<?> builder){
		id = PlugIn.id(element);
		children.clear();
		for( XMLElement elem:element.children()){
			PlugIn p = (PlugIn)builder.get(elem);
			if( p!=null ) children.add(p);
		}
	}
	
	public Collection<PlugIn> children(){ return children;}
	
	@Override
	public String id(){ return id; }

	@Override
	public void setId(String id){ this.id = id; }
	
	
}