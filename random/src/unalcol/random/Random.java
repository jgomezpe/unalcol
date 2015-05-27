package unalcol.random;

import unalcol.service.Service;

//
// Unalcol Random generation Pack 1.0 by Jonatan Gómez-Perdomo
// http://disi.unal.edu.co/profesores/jgomezpe/unalcol/random/
//
/**
 *
 * Random Generator Utility
 * <P>
 *
 * <P>
 * <A HREF="http://disi.unal.edu.co/profesores/jgomezpe/source/unalcol/random/Random.java">
 * Source code </A> is available.
 * <P>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2014 by Jonatan Gómez-Perdomo. <br>
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
public abstract class Random<T> extends Service{
    public abstract T next();
    /**
     * Returns a set of random objects
     * @param v Array where objects will be stored
     * @param offset Array where objects will be stored
     * @param m The total number of random objects to be generated
     */
    public void raw(T[] v, int offset, int m) {
        for (int i = 0; i < m; i++) {
            v[i+offset] = next();
        }
    }

    /**
     * Returns a set of random objects
     * @param m The total number of random objects to be generated
     * @return A set of m random objects
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
    
    
    public static Object next( Object obj ){
        Random<?> service = (Random<?>)provider.default_service(Random.class,obj);
        if( service != null )   return service.next();
        return null;
    }
    
    public static Object[] raw( Object obj, int m ){
        Random<?> service = (Random<?>)provider.default_service(Random.class,Object.class);
        if( service != null ) return service.raw(m);        
        return null;
    }

    @SuppressWarnings("unchecked")
	public static void raw( Object obj, Object[] v, int offset, int m ){
        Random<Object> service = (Random<Object>)provider.default_service(Random.class,Object.class);
        if( service != null ) service.raw(v, offset, m);        
    }    
}