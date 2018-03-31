package unalcol.reflect.plugin;

import org.w3c.dom.Element;

public class PlugInXMLElement implements PlugInElement{
	protected Element e;
	
	public PlugInXMLElement( Element e ) { this.e=e; }
	
	@Override
	public String getAttribute(String key) { return e.getAttribute(key); }
}