package unalcol.reflect.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
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

public class PlugInLoader {
	protected String fileName;
	protected HashMap<String,ClassLoader>  loader = new HashMap<String,ClassLoader>();
	protected HashMap<String,Element>  element = new HashMap<String,Element>();
	
	public static final String set = "set";
	public static final String plugin = "plugin";
	public static final String className = "class";
    
	public void add( String plugin, ClassLoader mainLoader ) throws IOException{
		String xml = PlugInLoader.plugin+".xml";
		InputStream is;
		if( plugin.endsWith("/") ) is = new FileInputStream(plugin+xml);
		else{
			@SuppressWarnings("resource")
			JarFile jarFile = new JarFile(plugin);
			is = jarFile.getInputStream(jarFile.getEntry(xml));
		}
		URLClassLoader child = new URLClassLoader (new URL[]{new File(plugin).toURI().toURL()}, mainLoader);
		build(is, child);
		is.close();
	}

	public String info(String className, String attribute){
		Element e = element.get(className);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
	
	public Set<String> plugins(){
		return element.keySet();
	} 
	
	public Object newInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ClassLoader cl = loader.get(className);
		if(cl==null) return null;
		Class<?> classToLoad = Class.forName(className, true, cl);
		return classToLoad.newInstance();
	}
 
	protected Element element( Node node ){
		if( node.getNodeType()==Node.ELEMENT_NODE ) return ((Element)node);
		return null;
	}
    
	protected void build(NodeList list, ClassLoader loader){
		int n = list.getLength();
		for(int i=0; i<n; i++){
			Element e = element(list.item(i)); 
			if(e!=null) System.out.println(e.getAttribute(className));
			if(e!=null)  build(element(list.item(i)), loader);
		}	
	}	
	
	protected void build(Element element, ClassLoader loader){
		if( element.getTagName().equals(set)) build(element.getChildNodes(), loader);
		else if( element.getTagName().equals(plugin)){
			this.loader.put(element.getAttribute(className),loader);	
			this.element.put(element.getAttribute(className), element);
		}
	}
    
	public void build(InputStream is, ClassLoader classLoader){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList e = doc.getElementsByTagName(set);
			build(element(e.item(0)), classLoader);
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
