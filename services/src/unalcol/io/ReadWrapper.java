package unalcol.io;

import java.io.IOException;
import java.lang.reflect.Method;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* ReadWrapper
* <p>Read service for classes that have implemented a read method.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/io/ReadWrapper.java" target="_blank">
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
public class ReadWrapper extends Read<Object> {
    /**
     * The id of the  wrapped read method
     */
    protected String method_name = "read";
    
    /**
     * The actual read method
     */
    protected Method method = null;
    
    /**
     * Creates a ReadWrapper service for the given class. The class should have implemented a read method.
     * @param type  Class that will have associated the read service.
     */
    public ReadWrapper( Class<?> type ) {
	try{
	    method = type.getMethod(method_name) ;
	    Class<?>[] param = method.getParameterTypes();
	    if(param.length!=1 || param[0] != ShortTermMemoryReader.class){
		method = null;
		throw new Exception("Incorrect number of arguments method read for class " + type.getName());
	    }
	}catch( Exception e ){            
	    e.printStackTrace();
	}
    }
    
    /**
     * Determines if the class has implemented a read method.
     * @return <i>true</i> if the class associated to the service has implemented a read method, <i>false</i> otherwise.
     */
    public boolean available(){
	return method!=null; 
    }

    /**
     * Reads an instance of the class associated to the service from the <i>reader</i>, if possible.
     * @param reader The ShortTermMemoryReader from which the instance will be read.
     * @return An instance of the class associated to the service that is read from the <i>reader</i>.
     * @throws IOException if an error reading the instance occurred.  
     */
    @Override
    public Object read(ShortTermMemoryReader reader) throws IOException {
	try{
	    return method.invoke(reader);
	}catch( Exception e ){            
	    throw new IOException(e.getMessage());
	}
    }
}