package unalcol.xml;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.net.URL;
import java.util.jar.JarFile;

//https://github.com/jgomezpe/unalcol/tree/master/core/
//
/**
*
* XMLUtil<T>
* <p>Utility functions for transforming XML objects to unalcol XMLElements.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/tree/master/core/src/unalcol/xml/XMLUtil.java" target="_blank">
* Source code </A> is available.
*
* <h3>License</h3>
*
* Copyright (c) 2014 by Jonatan Gomez-Perdomo. <br>
* All rights reserved. <br>
*
* <p>Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* <ul>
* <li> Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
* <li> Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* <li> Neither the name of the copyright owners, their employers, nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
* </ul>
* <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*
*
*
* @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
* @version 1.0
*/
public class XMLUtil {
	public static InputStream is(String url, String xmlFile ) throws IOException{
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