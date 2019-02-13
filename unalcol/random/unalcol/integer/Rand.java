package unalcol.integer;

import unalcol.random.Generator;


//
// Unalcol Random generation Pack 1.0 by Jonatan Gomez-Perdomo
// https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * RandInt
 * <P>Abstract random generator of integer numbers</p>
 *
 * <P>
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/tree/master/random/src/unalcol/random/integer/RandInt.java">
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
public interface Rand extends Generator<Integer>{	
	/**
	 * Returns a set of random integer numbers
	 * @param v Array where integer numbers will be stored
	 * @param m The total number of integer numbers
	 */
	public default void generate(int[] v, int offset, int m) {
	    for (int i = 0; i < m; i++) v[i+offset] = next();
	}
	
	/**
	 * Returns a set of random integer numbers
	 * @param m The total number of random integer numbers
	 * @return A set of m random integer numbers
	 */
	public default int[] generate(int m) {
		int[] v = null;
		if (m > 0) {
		    v = new int[m];
		    generate(v, 0,  m);
		}
		return v;
	}    
}