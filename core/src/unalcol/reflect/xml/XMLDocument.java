package unalcol.reflect.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarFile;

public class XMLDocument extends XMLElementWrap{
	protected String xmlFile;
	
	protected static InputStream is(String url, String xmlFile ) throws IOException{
		InputStream is;
		if( !xmlFile.endsWith(".xml") )  xmlFile += ".xml";
		if( url.endsWith("/") ) is = new URL(url+xmlFile).openStream();
		else{
			URL _url = new URL(url);
			if( _url.getProtocol().equals("file:") ){ 
				@SuppressWarnings("resource")
				JarFile jarFile = new JarFile(_url.getPath()); 
				is = jarFile.getInputStream(jarFile.getEntry(xmlFile));
			}else is = new URL("jar:"+url+"!/"+xmlFile).openStream();
		}
		return is;
	}
	
	public XMLDocument(String url, String xmlFile) throws IOException{
		this(XMLDocument.is(url,xmlFile));
	}
	
	public XMLDocument(InputStream is) throws IOException{
		super( XMLUtil.load(is).getDocumentElement() );
		is.close();
	}
	
	public XMLDocument(String xmlStr){ super( XMLUtil.load(xmlStr).getDocumentElement() ); }	
}
