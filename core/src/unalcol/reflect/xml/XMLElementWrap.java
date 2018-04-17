package unalcol.reflect.xml;

import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.SearchCollection;
import unalcol.types.collection.keymap.HTKeyMap;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLElementWrap implements XMLElement{
	protected Vector<XMLElement> children = new Vector<XMLElement>();
	protected HTKeyMap<String,Object> attributes = new HTKeyMap<String,Object>();
	
	public XMLElementWrap(){}
	
	public XMLElementWrap( Element e ){ this.init(e); }
	
	protected void init( Element e ){
		NamedNodeMap attrs = e.getAttributes();
		int n = attrs.getLength();
		for( int i=0; i<n; i++ ){
			String key = attrs.item(i).getNodeName();
			attributes.set(key, e.getAttribute(key));
		}
		attributes.set(TAG,e.getTagName());
		NodeList list = e.getChildNodes();
		n = list.getLength();
		for(int i=0; i<n; i++){
			Node node = list.item(i); 
			if(node.getNodeType()==Node.ELEMENT_NODE){ children.add(new XMLElementWrap((Element)node)); }
		}
	}
	
	@Override
	public SearchCollection<String,Object> attributes(){ return attributes; }

	@Override
	public SearchCollection<Integer, XMLElement> children(){ return children; }

	@Override
	public void setAttribute(String key, Object obj){ attributes.set(key, obj); }
}