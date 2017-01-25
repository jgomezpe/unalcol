package unalcol.io;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import unalcol.service.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Write 
* <p>Writing service. Writes objects to a {@link java.io.Writer}</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/io/Write.java" target="_blank">
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
public abstract class Write<T>{
    /**
     * Writes an object to the given writer
     * @param obj Object to write
     * @param writer The writer object
     * @throws IOException IOException
     */
    public abstract void write(T obj, Writer writer) throws Exception;
    
    /**
     * Gets the writing service used by the object <i>owner</i>.
     * @param owner Object owning the writing service.
     * @return The writing service used by the object.
     */
    public static Write<?> get(Object owner){
        if( ServiceCore.get(Object.class, Write.class) == null )
            set(Object.class, new WriteWrapper());
        return (Write<?>)ServiceCore.get(owner, Write.class);
    }
    
    /**
     * Sets the writing service used by object <i>owner</i> to service <i>service</i>.
     * @param owner Object owning the writing service.
     * @param service Writing service that will be used by object <i>owner</i>.
     * @return <i>true</i> if the <i>service</i> can be used by object owner, <i>false</i> otherwise.
     */
    public static boolean set( Object owner, Write<?> service ){
        return ServiceCore.set(owner, Write.class, service);
    }
        
    /**
     * Writes an object to the given writer (The object should has a write method)
     * @param obj Object to write
     * @param writer The writer object
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
	public static void apply(Object obj, Writer writer) throws Exception {
        Write<?> service = (Write<?>)get(obj);
        ((Write<Object>)service).write(obj, writer);
    }


    /**
     * Gets the persistent version of an object in String version. The Class which the
     * object belongs to should have associated a ClassPersistence object in the
     * Persistence class
     * @param obj Object that will be stored in an string
     * @return String containing the persistent version of the object
     */
    public static String toString(Object obj) {
        try {
            StringWriter sw = new StringWriter();
            apply(obj, sw);
            sw.close();
            return sw.toString();
        } catch (Exception e) {}
        return obj.toString();
    }       
}