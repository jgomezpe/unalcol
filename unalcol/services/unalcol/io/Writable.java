/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.io;

import java.io.IOException;
import java.io.Writer;

import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;
import unalcol.services.Service;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Writable
* <p>Writes objects to a {@link java.io.Writer} implementing the write method.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/io/Writable.java" target="_blank">
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
public interface Writable {
	/**
	 * Writes the object to a writer (The object should has a write method)
	 * @param writer The writer object
	 * @throws IOException IOException
	 */
	default void write(Writer writer) throws ParamsException{ service().write(this, writer); }
	
	default Write service(){ return Writable.service(this); }
	
	public static Write service(Object caller ){
		Write w;
		try{
			w = (Write)Service.provider(Write.class, caller);
		}catch(Exception e){
			w = new Write(){
				@Override
				public void write(Object obj, Writer writer) throws ParamsException 
				{ try{ writer.write(obj.toString() ); }catch( IOException e ){ throw ParamsException.wrap(InnerCore.IO, e); } }

				@Override
				public String toString(){ return "Write"; }
	    	};
	    	Service.register(w, Object.class);
		}
		return w;
	}
	
	public static Writable cast( Object obj ){
		if( obj instanceof Writable ) return (Writable)obj;
		return new Writable(){
			public Write service(){ return Writable.service(obj); } 
			public void write(Writer writer) throws ParamsException{ service().write(obj, writer); }
		};
	}
}