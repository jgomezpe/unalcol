package unalcol.reflect.plugin;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class PlugInXMLElement implements PlugInDescriptor{
	protected Element e;
	protected ClassLoader loader;
	protected PlugInDescriptor[] children=null;
	
	public PlugInXMLElement( Element e ) {
		this.e=e; 
		NodeList list = e.getChildNodes();
		int n = list.getLength();
		int k = 0;
		for(int i=0; i<n; i++){
			e = XMLUtil.element(list.item(i)); 
			if(e!=null){ k++; }
		}
		children = new PlugInDescriptor[k];
		k=0;
		for(int i=0; i<n; i++){
			e = XMLUtil.element(list.item(i)); 
			if(e!=null){
				children[k] = new PlugInXMLElement(e);
				k++; 
			}
		}
	}
	
	@Override
	public String getAttribute(String key) { return e.getAttribute(key); }

	@Override
	public void setLoader( ClassLoader loader ){ this.loader = loader; }
	
	@Override
	public ClassLoader getLoader(){ return loader;	}

	@Override
	public PlugInDescriptor child(int k) { return children[k];	}

	@Override
	public int children() {	return children.length; }
	
	public String nick(){ return e.getTagName(); };	
}