package unalcol.random;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.service.ServiceCore;

//
// Unalcol Random generation Pack 1.0 by Jonatan Gomez-Perdomo
// https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/random/
//
/**
 *
 * Random
 * <P>Random Generator Utility
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/random/Random.java">
 * Source code </A> is available.
 * <P>
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
public abstract class Random<T>{
	/**
	 * Generates a random object of class <i>T</i>.
	 * @return A random object of class <i>T</i>.
	 */
	public abstract T next();
    
	/**
	 * generates a random objects array of class <i>T</i>.
	 * @param v Array where objects will be stored.
	 * @param offset Initial position in the array for the generated objects.
	 * @param m The total number of random objects to be generated.
	 */
	public void raw(T[] v, int offset, int m) {
	    for (int i = 0; i < m; i++) v[i+offset] = next();
	}
	
	/**
	 * Generates a random objects array of class <i>T</i>.
	 * @param m The total number of random objects to be generated
	 * @return A random objects array (size <i>m</i>) of class <i>T</i>.
	 */
	@SuppressWarnings("unchecked")
	public T[] raw(int m) {
		T[] v = null;
		if (m > 0) {
			v = (T[])new Object[m];
			raw(v, 0, m);
		}
		return v;
	}
    
    // Most used random generated types
	/**
	 * The by default RawGenerator used by the Random generator. 
	 * @return The by default RawGenerator used by the Random generator.
	 */
	protected static RawGenerator raw(){
		RawGenerator raw = RawGenerator.get(Random.class);
		if( raw == null ){ 
			raw = new JavaGenerator();
			RawGenerator.set(Random.class, raw);
		}
		return raw;
	}
	
	/**
	 * Generates a x~U[0,1) double number.
	 * @return A x~U[0,1) double number.
	 */
	public static double nextDouble(){
		return raw().next();
	}
	
	/**
	 * Generates a x~U[0,max) integer number.
	 * @param max The upper bound of the open-close interval for generating integer numbers.  
	 * @return A x~U[0,max) integer number.
	 */
	public static int nextInt( int max ){
		return raw().integer(max);
	}
	
	/**
	 * Generates a <i>false</i> value with probability <i>falseProbability</i> and
	 * a <i>true</i> value with probability <i>1-falseProbability</i>. 
	 * @param falseProbability The probability of generating a </ifalse</i> value.
	 * @return A <i>false</i> value with probability <i>falseProbability</i> and
	 * a <i>true</i> value with probability <i>1-falseProbability</i>.
	 */
	public static boolean nextBool( double falseProbability ){
		return raw().bool(falseProbability);
	}
	
	/**
	 * Generates a <i>false</i> value with probability <i>0.5</i> and
	 * a <i>true</i> value with probability <i>0.5</i>. 
	 * @return A <i>false</i> value with probability <i>0.5</i> and
	 * a <i>true</i> value with probability <i>0.5</i>.
	 */
	public static boolean nextBool( ){
		return nextBool(0.5);
	}
    
	// Defining it as a service
	/**
	 * Obtains the Random generator associated to a given object (<i>owner</i>).
	 * @param owner The object from which the associated Random generator will be obtained.
	 * @return The Random generator associated to a given object (<i>owner</i>).
	 */
	public static Random<?> get( Object owner ){
		return (Random<?>)ServiceCore.get(owner, Random.class);
	}
        
	/**
	 * 
	 * @param owner
	 * @return
	 */
	public static Object next( Object owner ){
	    Random<?> service = get(owner);
	    if( service != null )   return service.next();
	    return null;
	}
	
	public static Object[] raw( Object owner, int m ){
		Random<?> service = get(owner);
		if( service != null ) return service.raw(m);        
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void raw( Object owner, Object[] v, int offset, int m ){
		Random<Object> service = (Random<Object>)get(owner);
		if( service != null ) service.raw(v, offset, m);        
	}    
}