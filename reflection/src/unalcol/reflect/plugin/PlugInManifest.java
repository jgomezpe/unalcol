package unalcol.reflect.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PlugInManifest extends PlugInSet{
	public PlugInManifest( String pluginFile ) throws IOException{
		String xml = PlugIn.plugin+".xml";
		InputStream is;
		if( pluginFile.endsWith("/") ) is = new URL(pluginFile+xml).openStream();
		else{
			URL url = new URL(pluginFile);
			if( url.getProtocol().equals("file:") ){ 
				@SuppressWarnings("resource")
				JarFile jarFile = new JarFile(url.getPath()); 
				is = jarFile.getInputStream(jarFile.getEntry(xml));
			}else{
				is = new URL("jar:"+pluginFile+"!/"+xml).openStream();
			}
		}
		build(is);
		is.close();
	}

	protected void build(NodeList list){
		int n = list.getLength();
		for(int i=0; i<n; i++){
			Element e = XMLUtil.element(list.item(i)); 
			if(e!=null)  add(e);
		}	
	}	
	
	public void build(InputStream is){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			build( doc.getElementsByTagName(PlugIn.plugin) );
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