package unalcol.reflect.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.jar.JarFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PlugInManifest {
	public static final String set = "set";
	public static final String plugin = "plugin";
	public static final String className = "class";
	
	protected HashMap<String,Element>  element = new HashMap<String,Element>();

	public PlugInManifest( String plugin ) throws IOException{
		String xml = PlugInLoader.plugin+".xml";
		InputStream is;
		if( plugin.endsWith("/") ) is = new URL(plugin+xml).openStream();
		else{
			URL url = new URL(plugin);
			if( url.getProtocol().equals("file:") ){ 
				@SuppressWarnings("resource")
				JarFile jarFile = new JarFile(url.getPath()); 
				is = jarFile.getInputStream(jarFile.getEntry(xml));
			}else{
				is = new URL("jar:"+plugin+"!/"+xml).openStream();
			}
		}
		build(is);
		is.close();
	}
	
	public String info(String className, String attribute){
		Element e = element.get(className);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
		
	public Set<String> plugins(){ return element.keySet(); } 	

	protected Element element( Node node ){
		if( node.getNodeType()==Node.ELEMENT_NODE ) return ((Element)node);
		return null;
	}
    
	protected void build(NodeList list){
		int n = list.getLength();
		for(int i=0; i<n; i++){
			Element e = element(list.item(i)); 
			if(e!=null)  build(element(list.item(i)));
		}	
	}	
	
	public Element get(String plugin){ return element.get(plugin); }
		
	protected void build(Element element){
		if( element.getTagName().equals(set)) build(element.getChildNodes());
		else if( element.getTagName().equals(plugin)) this.element.put(element.getAttribute(className), element);
	}
    
	public void build(InputStream is){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList e = doc.getElementsByTagName(set);
			build(element(e.item(0)));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}