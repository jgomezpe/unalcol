package unalcol.instance;

import java.lang.reflect.Constructor;
import unalcol.services.MicroService;

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
public class Instance<T>  implements MicroService{
	public static final String name="instance";

	public Object apply( Class<?> type, Object... args ){ return create(type, args); }    

	@Override
	public Object apply(Object obj, Object... args){ return apply(obj.getClass(),args); }    	
    
	/**
	 * Generates an instance belonging to the class <i>type</i> according to the parameters (it does not support VarArgs constructors).
	 * @param type Class of instances that will be generated.
	 * @param args Arguments for creating an instance.
	 * @return An instance belonging to the class <i>type</i> using the parameters.
	 */
	public static Object create( Class<?> type, Object... args ){
		Class<?>[] argsTypes = new Class<?>[args.length];
		for( int i=0; i<argsTypes.length; i++) argsTypes[i] = args[i].getClass();
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
					if( exact ) return  c[i].newInstance(args);
					if( assignable ) cc = c[i];
				}
			}
			if( cc != null )	return cc.newInstance(args);
		}catch( Exception e ){}	
		return null;
	}
}