package unalcol.instance;

import java.lang.reflect.Constructor;

import unalcol.service.ServiceCore;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Instance
* <p>Generates instances of a given Class.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/instance/Instance.java" target="_blank">
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
* @param <T> Type of objects from which instances will be generated.
*/
public abstract class Instance<T> {
	/**
     * Creates an instance belonging to the class <i>T</i>.
     * @param args Arguments for creating an instance.
     * @return An instance belonging to the class <i>T</i>.
     */
	public abstract T get( Object... args);
    
	/**
     * Obtains the class from which instances are created.
     * @return The class from which instances are created.
     */
	public abstract Class<?> type();
    
    
    // Defining as a Service 
    
	/**
     * Obtains the Instances generator (instances belonging to the class <i>type</i>) that is used by <i>owner</i>.
     * @param owner Owner of the Instance generator (instances belonging to the class <i>type</i>)
     * @param type Class of instances that the Instance generator is generating.
     * @return The Instances generator (instances belonging to the class <i>type</i>) that is used by <i>owner</i>.
     */
	public static Instance<?> get(Object owner, Class<?> type){
		Instance<?>[] opt = (Instance<?>[])ServiceCore.getAll(owner, Instance.class);
		int i=0;
		while( i<opt.length && opt[i].type() != type ) i++; 
		return (i<opt.length)? opt[i] : null;
	}
    
	/**
     * 
     * @param owner Owner that will be associated to the Instance generator (instances belonging to the class <i>type</i>)
     * @param service Instances generator (instances belonging to the class <i>type</i>) that will be used by <i>owner</i>.
     * @return if the instance <i>service</i> was associated to the <i>owner</i>.
     */
	public static boolean set( Object owner, Instance<?> service ){
		return ServiceCore.set(owner, service.getClass(), service);
	}
    
	/**
     * Generates an instance belonging to the class <i>type</i> using the Instance generator that is used by <i>owner</i>.
     * @param owner Owner of the Instance generator (instances belonging to the class <i>type</i>)
     * @param type Class of instances that will be generated.
     * @param args Arguments for creating an instance.
     * @return An instance belonging to the class <i>type</i> using the Instance generator that is used by <i>owner</i>.
     */
	@SuppressWarnings("unchecked")
	public static Object create( Object owner, Class<?> type, Object... args ){
		Instance<?> service = get(owner, type);
		if( service != null ) return ((Instance<Object>)service).get(args);
		return create(type, args);
	}

	/**
     * Generates an instance belonging to the class <i>type</i> according to the parameters (it does not support VarArgs constructors).
     * @param type Class of instances that will be generated.
     * @param args Arguments for creating an instance.
     * @return An instance belonging to the class <i>type</i> using the parameters.
     */
	public static Object create( Class<?> type, Object... args ){
		Class<?>[] argsTypes = new Class<?>[args.length];
		for( int i=0; i<argsTypes.length; i++){
			argsTypes[i] = args[i].getClass();
		}
		try{
			Constructor<?>[] c = type.getConstructors();
			Constructor<?> cc = null;
			// @TODO: Check var ags constructors.. 
			for( int i=0; i<c.length; i++ ){
				Class<?>[] parms = c[i].getParameterTypes();
				if( parms.length==args.length ){
					boolean assignable = true;
					boolean exact = true;
					for( int k=0; k<args.length && assignable; k++ ){
						assignable = parms[k].isAssignableFrom(argsTypes[k]);
						exact = parms[k] == argsTypes[k];
					}
					if( exact ){
						Object x = c[i].newInstance(args);
						return x;
					}	
					if( assignable ) cc = c[i];
				}
			}
			if( cc != null )
				return cc.newInstance(args);
		}catch( Exception e ){
		}	
		return null;
	}    	
}