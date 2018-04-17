package unalcol.reflect.util;

import java.lang.reflect.Array;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Loader
* <P>Wrap for a ClassLoader.
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/util/Loader.java" target="_blank">
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
public class Loader {
	/**
	 * Loads an Array class from an string
	 * @param className String with an Array class name following the Java convention.
	 * @return An Array class if <i>className</i> string defines one.
	 * @throws ClassNotFoundException If the <i>className</i> string does not define an array class.
	 */
	protected static Class<?> loadArrayClass( String className ) throws ClassNotFoundException{
		if( className.charAt(0)=='[' ){
			Class<?> cl = loadArrayClass(className.substring(1));
			return Array.newInstance(cl, 0).getClass();
		}else{
			if( className.length()==1 ){
				switch(className.charAt(0)){
					case 'Z': return Boolean.TYPE;
					case 'B': return Byte.TYPE;
					case 'C': return Character.TYPE; 
					case 'D': return Double.TYPE;
					case 'F': return Float.TYPE;
					case 'I': return Integer.TYPE;
					case 'J': return Long.TYPE;
					case 'S': return Short.TYPE;
				}
			}else{
				if(className.length()>0 && className.charAt(0)=='L' && className.charAt(className.length()-1)==';')
					return loadClass( className.substring(1, className.length()-1) );
			}	
			throw new ClassNotFoundException(className);
		}
	}

	/**
	 * Loads a Class from an String representing the name of the class.
	 * @param className An String representing the class name.
	 * @return The class represented by the string <i>className</i>
	 * @throws ClassNotFoundException If the string <i>className</i> does not represents a valid class Name or a class.
	 */
	public static Class<?> loadClass( String className ) throws ClassNotFoundException{
		if( className.charAt(0)=='[' ){
			return loadArrayClass(className);
		}
		return ClassLoader.getSystemClassLoader().loadClass(className);
	}
}