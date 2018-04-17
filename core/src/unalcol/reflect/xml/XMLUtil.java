package unalcol.reflect.xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLUtil {
	public static Document load(String xmlStr){ return load( new ByteArrayInputStream(xmlStr.getBytes(StandardCharsets.UTF_8)) ); }
	
	public static Document load(InputStream is){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(is);
		} catch (ParserConfigurationException | SAXException | IOException e) {	e.printStackTrace(); }
		return null;
	}
	
	public static XMLElement document( Document doc ){ return new XMLElementWrap(doc.getDocumentElement()); }
	
	public static XMLElement document( String xmlStr ){ return document(load(xmlStr)); }
	
	public static XMLElement document( InputStream is ){ return document(load(is)); }
}