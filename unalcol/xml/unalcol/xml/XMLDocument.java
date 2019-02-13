package unalcol.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

//
//Unalcol core structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/core/
//
/**
*
* XMLDocument
* <p>Unalcol version of a XML document node.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/tree/master/core/src/unalcol/xml/XMLDocument.java" target="_blank">
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
public class XMLDocument extends XMLElementWrap{
	
	protected static InputStream init( URL url ) throws ParamsException{
		try{ return url.openStream(); }catch(IOException e){ throw ParamsException.wrap(InnerCore.IO, e); }
	}
	protected static InputStream init(String url, String xmlFile) throws ParamsException{
		try{ return XMLUtil.is(url,xmlFile); }catch(IOException e){ throw ParamsException.wrap(InnerCore.IO, e); }
	}
	
	public XMLDocument(String url, String xmlFile) throws ParamsException{ this(init(url,xmlFile)); }
	
	public XMLDocument(URL url) throws ParamsException{ this( init(url) ); }
	
	public XMLDocument(InputStream is) throws ParamsException{
		super( XMLUtil.load(is).getDocumentElement() );
		try{is.close(); }catch(IOException e){ throw ParamsException.wrap(InnerCore.IO, e); }
	}
	
	public XMLDocument(String xmlStr){ super( XMLUtil.load(xmlStr).getDocumentElement() ); }	
}