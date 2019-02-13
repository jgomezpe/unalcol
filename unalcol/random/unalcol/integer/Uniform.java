package unalcol.integer;

import unalcol.random.InverseGenerator;

// Unalcol Random generation Pack 1.0 by Jonatan Gomez-Perdomo
// https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * IntUniform
 * <P>Generates integer numbers following an uniform probability distribution</p>
 *
 * <P>
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/tree/master/random/src/unalcol/random/integer/IntUniform.java">
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
public class Uniform extends InverseGenerator<Integer> implements Rand{
	/**
	 * Low Limit
	 */
	protected int min;

	/**
	 * Interval Length
	 */
	protected int length;
	
	/**
	 * Creates a uniform integer number generator in the interval [0,max)
	 * @param max Sup Limit
	 */
	public Uniform(int max) {
		this.min = 0;
		this.length = max;
	}
	
	/**
	 * Creates a uniform integer number generator in the interval [min,max)
	 * @param min Low limit
	 * @param max Sup limit
	 */
	public Uniform(int min, int max) {
		this.min = min;
		this.length = max - min;
	}
	
	public void set( int max ){ set( 0, max ); }

	public void set( int min, int max ){
		if( min>max ){
			int temp = min;
			min=max;
			max=temp;
		}
		this.min = min;
		this.length = max-min; 
	}
	
	/**
	 * Generates a uniform integer number in the interval [min,max)
	 * @return A uniform integer number in the interval [min,max)
	 */
	@Override
	public Integer next(double x) {	return (min + (int)(length*x));	}
}