package unalcol.clone;

import java.lang.reflect.Method;

import unalcol.instance.Instance;

import java.lang.reflect.Array;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* CloneWrapper
* <P>Cloning service wrapper for the clone method. Used for classes that already define a clone method*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/clone/CloneWrapper.java" target="_blank">
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
public class CloneWrapper extends Clone<Object> {
	/**
     * Creates a clone wrapped method for objects
     */
	protected String method_name = "clone";
    
	/**
     * Creates a clone (non shallow copy) of a Java primitive types array 
     * @param obj Array of a primitive type values to be cloned 
     * @return A clone of the array (non shallow copy)
     */
	protected Object clonePrimitiveArray( Object obj ){
		if( obj instanceof int[] ) return ((int[])obj).clone();
		if( obj instanceof double[] ) return ((double[])obj).clone();
		if( obj instanceof char[] ) return ((char[])obj).clone();
		if( obj instanceof byte[] ) return ((byte[])obj).clone(); 
		if( obj instanceof long[] ) return ((long[])obj).clone();
		if( obj instanceof short[] ) return ((short[])obj).clone();
		if( obj instanceof float[] ) return ((float[])obj).clone(); 
		return ((boolean[])obj).clone();
	}
    
	/**
     * Creates a clone of an array. 
     * It uses the clone service defined for each object in the array.
     * @param obj Array to be cloned 
     * @return A clone of the array
     */
	protected Object cloneArray( Object obj ){
		Class<?> cl = obj.getClass().getComponentType();
		if( cl.isPrimitive() ) return clonePrimitiveArray(obj);
		int n = Array.getLength(obj);
		Object clone = Array.newInstance(cl, n);
		for( int i=0; i<n; i++ ){
			Object the_obj = Array.get(obj, i); 
			if( the_obj != null ){ 
				Array.set(clone, i, Clone.create(the_obj));
			}else{ 
				Array.set(clone, i, null);
			}
		}
		return clone;
	}
    
	/**
     * Creates a clone of the given object if a clone method is provided by the object
     * @param obj Object from which the Clone service will be retrieved
     * @return A Clone of the object  if a clone method is provided by the object
     * <i>null</i> otherwise
     */
	@Override
	public Object clone(Object obj){
		if( obj instanceof String ) return ""+obj;
		
		if( obj instanceof Double || obj instanceof Integer || 
			 obj instanceof Character || obj instanceof Long )
            return obj;
        
		if( obj.getClass().isArray() ) return cloneArray(obj);

		try{
			Method m = obj.getClass().getMethod(method_name) ;
			return m.invoke(obj);
		}catch( Exception e ){            
			return Instance.create(obj.getClass(), obj);
		}
	}
}