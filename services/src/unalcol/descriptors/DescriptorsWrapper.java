package unalcol.descriptors;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* DescriptorsWrapper
* <p>Used for classes that already define a descriptors method.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/descriptors/DescriptorsWrapper.java" target="_blank">
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
public class DescriptorsWrapper extends Descriptors<Object> {
	/**
	 * The method name that can be used for describing an object (if available in the wrapped class)
	 */
	protected String method_name = "descriptors";
	
	/**
     * Creates a descriptors array of a boolean, byte, short, int, long, float, or double array 
     * @param obj Array of a primitive type values to be described 
     * @return A clone of the array (non shallow copy)
     */
	protected double[] descriptorsPrimitiveArray( Object obj ){
		if( obj instanceof double[] ) return ((double[])obj).clone();

		if( obj instanceof int[] ){
			int[] x = (int[])obj;
			double[] d = new double[x.length];
			for( int i=0; i<d.length; i++) d[i] = x[i];
			return d;
		}
		
		if( obj instanceof byte[] ){
			byte[] x = (byte[])obj;
			double[] d = new double[x.length];
			for( int i=0; i<d.length; i++) d[i] = x[i];
			return d;
		}
		
		if( obj instanceof long[] ){
			long[] x = (long[])obj;
			double[] d = new double[x.length];
			for( int i=0; i<d.length; i++) d[i] = x[i];
			return d;
		}
        
		if( obj instanceof short[] ){
			short[] x = (short[])obj;
			double[] d = new double[x.length];
			for( int i=0; i<d.length; i++) d[i] = x[i];
			return d;
		}
        
		if( obj instanceof float[] ){
			float[] x = (float[])obj;
			double[] d = new double[x.length];
			for( int i=0; i<d.length; i++) d[i] = x[i];
			return d;
		}
        
		if( obj instanceof boolean[] ){
			boolean[] x = (boolean[])obj;
			double[] d = new double[x.length];
			for( int i=0; i<d.length; i++) d[i] = x[i]?1.0:0.0;
			return d;
		}
		return null;
	}
    	
	/**
     * Creates a descriptors array for an array of objects. 
     * @param obj Array of objects to be described.
     * @return Descriptors array for an array of objects.
     */
	protected double[] descriptorsArray( Object obj ){
		Class<?> cl = obj.getClass().getComponentType();
		if( cl.isPrimitive() ) return descriptorsPrimitiveArray(obj);
        
		int m = 0;
		int n = Array.getLength(obj);
		double[][] desc = new double[n][];
		for( int i=0; i<n; i++ ){
			desc[i] = Descriptors.obtain(Array.get(obj, i));
			m += desc[i].length;
		}
		double[] d = new double[m];
		m=0;
		for( int i=0; i<n; i++ ){
			System.arraycopy(desc[i], 0, d, m, desc[i].length);
			m += desc[i].length;
		}
		return d;
	}
    
	/**
     * Obtains the descriptors of an object
     * @param obj Object to be analyzed
     * @return An array of double values used for describing the object
     */
	@Override
	public double[] descriptors(Object obj) {
		if( obj.getClass().isArray() ) return descriptorsArray(obj);

		try {
			Method m = obj.getClass().getMethod(method_name) ;
			return (double[]) m.invoke(obj);
		} catch (Exception e) {}
		return null;
	}
}