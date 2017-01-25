package unalcol.tracer;

import unalcol.service.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Tracer
* <P> Abstract definition of an object's Tracer (stores useful information of objects).
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/tracer/Tracer.java" target="_blank">
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
public abstract class Tracer{
	/**
	 * Determines if objects are being traced or not.
	 */
    protected boolean tracing = true;

    /**
     * Default constructor
     */
    public Tracer(){}

    /**
     * Starts the tracing of objects process
     * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
     */
    public boolean start(){
        boolean old = tracing;
        tracing = true;
        return old;
    }

    /**
     * Stops the tracing of objects process
     * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
     */
    public boolean stop(){
        boolean old = tracing;
        tracing = false;
        return old;
    }

    /**
     * Adds an object sent by an object to the tracer
     * @param obj Traced information to be added
     */
    public abstract void add(Object... obj);

    /**
     * Returns the traced object
     * @return An object representing the traced information
     */
    public abstract Object get();

    /**
     * Cleans the traced information
     */
    public abstract void clean();

    /**
     * Closes the tracer
     */
    public abstract void close();

    /**
     * Add an object that can call the associated tracing process.
     * @param owner Object that can call the tracing process.
     * @param tracer Trace process that can be used by the object.
     * @return <i>true</i>If the tracer process can be associated to the given object, <i>false</i>otherwise. 
     */
    public static boolean addTracer( Object owner, Tracer tracer ){
    	return ServiceCore.set(owner, tracer);
    }
    
    /**
     * Obtains a list of the tracing process that can be carried on by an object
     * @param owner The object that can carry on the trace process
     * @return A list of the tracing process that can be carried on by an object, the list will have length zero  
     * if the object has not trace process that can carry on.
     */
    public static Tracer[] get( Object owner ){
        try{
           Object[] services = ServiceCore.getAll(owner, Tracer.class);
           Tracer[] tracers = new Tracer[services.length];
           for( int i=0; i<services.length; i++ ){
               tracers[i] = (Tracer)services[i];
           }
           return tracers;
        }catch( Exception e ){
            e.printStackTrace();
        }
        return new Tracer[0];
    }

    /**
     * Adds a traceable data object to each tracer associated to a given object
     * @param obj Object carrying on the trace process
     * @param data Object to be added to each tracer associated to the object
     */
    public static void trace(Object obj, Object... data) {
        Tracer[] services = get(obj);
        for (Tracer service : services) {
            service.add(data);
        }
    }

    /**
     * Closes each tracer associated to a given object
     * @param obj Object being traced
     */
    public static void close(Object obj) {
        Tracer[] services = get(obj);
        for (Tracer service : services) {
            service.close();
        }
    }

    /**
     * Cleans each tracer associated to a given object
     * @param obj Object being traced
     */
    public static void clean(Object obj) {
        Tracer[] services = get(obj);
        for (Tracer service : services) {
            service.clean();
        }
    }
}