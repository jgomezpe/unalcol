package unalcol.integer;

import unalcol.random.InverseGenerator;

//
// Unalcol Random generation Pack 1.0 by Jonatan Gomez-Perdomo
// https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * IntRoulette
 * <P>Generates integer numbers following a Weighted probability density (Roulette)</p>
 *
 * <P>
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/tree/master/random/src/unalcol/random/integer/IntRoulette.java">
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
public class Roulette  extends InverseGenerator<Integer> implements Rand{
	/**
	 * Probability of generating an integer number [0,length(density))
	 */
	protected double[] density;
	
	/**
	 * Creates an integer number generator [0,n) with the following probability density:
	 * p(i) = (n-i)/sum(1,n)
	 * @param n number of different integer values that can be generated...
	 */
	public Roulette(int n) {
		density = new double[n];
        double total = n * (n + 1) / 2.0;
        for (int i = 0; i < n; i++) {
            density[i] = (n - i) / total;
        }
	}
	
	/**
	 * Creates an integer number generator with the given probability density
	 * @param density Probability of generating an integer number [0,length(density))
	 */
	public Roulette(double[] density) {
		this.density = density;
	}
	
	/**
	 * Generates an integer number following the associated density function
	 * @return An integer number following the associated density function
	 */
	@Override
	public Integer next(double x) {
		int length = density.length;
		int i = 0;
		while (i < length && x >= density[i]) {
			x -= density[i];
			i++;
		}
		return i;
	}

	/**
	 * Defines the density function of the generated integers
	 * @param density Probability of generating an integer number [0,length(density))
	 */
	public void setDensity(double[] density) {
		this.density = density;
	}
}