package unalcol.reflect.plugin;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtil {
	public static Element element( Node node ){	return ( node.getNodeType()==Node.ELEMENT_NODE )? ((Element)node) :  null;	}    
	public static Element[] children( Element element, String child_tag ){
		NodeList list=element.getChildNodes();
		int m=0;
		for(int i=0; i<list.getLength(); i++){
			Element e = element(list.item(i));
			if(e!=null && e.getTagName().equals(child_tag)) m++;
		}
		Element[] offspring = new Element[m];
		m=0;
		for(int i=0; i<list.getLength(); i++){
			Element e = element(list.item(i));
			if(e!=null && e.getTagName().equals(child_tag)){
				offspring[m] = e;
				m++;
			}
		}
		return offspring;
	}
	
	public static Document load(String xmlStr){ return load( new ByteArrayInputStream(xmlStr.getBytes(StandardCharsets.UTF_8)) ); }
	
	public static Document load(InputStream is){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {	e.printStackTrace(); }
		return null;
	}
	
}
