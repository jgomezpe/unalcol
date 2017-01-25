package unalcol.io;
import java.io.IOException;
import unalcol.service.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Read
* <p>Read service. Reads objects from a {@link unalcol.io.ShortTermMemoryReader}.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/io/Read.java" target="_blank">
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
* @param <T> Type of objects that will be read from the ShortTermMemoryReader.
*/
public abstract class Read<T>{
	/**
	 * Reads an object from the given reader
	 * @param reader The input stream from which the object will be read
	 * @return An object, of the type the read service is attending, that is read from the input stream
	 * @throws IOException IOException
	 */
	public abstract T read(ShortTermMemoryReader reader) throws IOException;
    
	/**
	 * Determines if the default service has been registered in the service infrastructure.
	 * @param owner The owner of the service.
	 * @return The read method owned by <i>owner</i>.
	 */
	public static Read<?> get(Class<?> owner){
	    Read<?> read = (Read<?>)ServiceCore.get(owner, Read.class);
	    if( read == null ){
			ReadWrapper rw = new ReadWrapper(owner);
			if( rw.available() ){
				set(owner, rw);
				read = rw;
			}
	    }
	    return read;
	}
    
	/** 
	 * Sets the read service associated to the given class
	 * @param owner Class that owns the service
	 * @param service  Read service to be associated to the given class
	 * @return <i>true</i> If the read service can be associated to the given class, <i>false</i> otherwise.
	 */
	public static boolean set( Class<?> owner, Read<?> service ){
		return ServiceCore.set(owner, Read.class, service);
	}        
	
	/**
	 * Reads an object from the given reader (The object should has a read method)
	 * @param obj Object to read
	 * @param reader The reader object
	 * @return An object that is read from the reader.
	 * @throws IOException IOException
	 */
	@SuppressWarnings("unchecked")
	public static Object apply(Class<?> obj, ShortTermMemoryReader reader) throws IOException {
		return ((Read<Object>)get(obj)).read(reader);
	}

	/**
	 * Reads space characters from a input reader up to finding the <i>separator</i> char.
	 * @param reader Input Reader
	 * @param separator Character consider separator of tokens
	 * @throws IOException An exception if it was not possible to read a separator sequence.
	 */
	public static void readSeparator( ShortTermMemoryReader reader, char separator ) throws IOException{
		try{
			char c = (char)reader.read();
			while( c!=separator && Character.isSpaceChar(c)) c = (char)reader.read();
			
			if( c != separator && c != (char)-1 ) throw new Exception("Non available separator...");
		}catch( Exception e ){
			throw reader.getException("ReadService Parser Error "+e.getMessage());
		}
	}
}