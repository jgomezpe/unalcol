package unalcol.random.raw.rngpack;
import java.util.*;

import unalcol.random.raw.*;

/**
 *
 * <A HREF="http://www.math.keio.ac.jp/matumoto/emt.htm">Mersenne Twister</A> --
 * advanced pseudorandom generator with a period of 2<SUP>19937</SUP>-1
 * <P>
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/random/raw/rngpack/RanMT.java">
 * Source code </A> is available.
 * <P>
 * This class is a ported version of the Paul Houle's
 * <A HREF="http://www.honeylocust.com/RngPack/"> RngPack 1.1a implementation</A>
 *
 *
 *
 *
 * <h3>License</h3>
 *
 * Ported to the unalcol library by Jonatan Gomez from a work copyright (c) 2003 by Paul Houle. <br>
 * Derived from a work copyright (c) 2003 by Sean Luke. <br>
 * Portions copyright (c) 1993 by Michael Lecuyer. <br>
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
 * This code is an adapted version of the RanMT
 * <A HREF="/RngPack/src/edu/cornell/lassp/houle/RngPack/Ranmar.java"> source code</A>
 * from RngPack 1.1a by Paul Houle
 *
 * @author <A HREF="http://www.honeylocust.com/"> Paul Houle </A>
 * (E-mail: <A HREF="mailto:paul@honeylocust.com">paul@honeylocust.com</A>)
 * Porting by <A HREF="http://dis.unal.edu.co/~jgomezpe"> Jonatan Gomez </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */


public class RanMT extends SeedableGenerator{

    private static final int N = 624;
    private static final int M = 397;
    private static final int MATRIX_A = 0x9908b0df;   //    private static final * constant vector a
    private static final int UPPER_MASK = 0x80000000; // most significant w-r bits
    private static final int LOWER_MASK = 0x7fffffff; // least significant r bits


    // Tempering parameters
    private static final int TEMPERING_MASK_B = 0x9d2c5680;
    private static final int TEMPERING_MASK_C = 0xefc60000;

    private int mt[]; // the array for the state vector
    private int mti; // mti==N+1 means mt[N] is not initialized
    private int mag01[];

    /**
     * Creates a RanMT generator with a time dependent seed (using the current time)
     */
    public RanMT() {
        this.initSeed(System.currentTimeMillis()%10000);
	//this.initSeed(4357);
    }

    /*
     *
     * Note that this uses only the LSB 32 bits from the long
     *
     */
    
    /**
     * Creates a RanMT generator with the given seed
     * @param seed The seed
     */
    public RanMT(long seed) {
	this.initSeed(seed);
    }

    /**
     * Creates a RanMT generator with the given time dependent seed
     * @param seed The time information used for defining the seed
     */
    public RanMT(Date d) {
	this.initSeed(d.getTime());
    }

    /**
     *
     * If a 32 bit seed isn't enough for you,  you can pass an array of
     * 624 integers.  Any array of integers is fine so long as they
     * aren't all zero.
     *
     */

    public RanMT(int array[]) {
	this.setSeed(array);
    }

    public long initSeed(long _seed){
        seed = _seed;
	mt = new int[N];

	mag01 = new int[2];
	mag01[0] = 0x0;
	mag01[1] = MATRIX_A;

        mt[0]= (int)(seed & 0xfffffff);
        for (mti=1; mti<N; mti++)
            {
		mt[mti] =
		    (1812433253 * (mt[mti-1] ^ (mt[mti-1] >>> 30)) + mti);
		/* See Knuth TAOCP Vol2. 3rd Ed. P.106 for multiplier. */
		/* In the previous versions, MSBs of the seed affect   */
		/* only MSBs of the array mt[].                        */
		/* 2002/01/09 modified by Makoto Matsumoto             */
		mt[mti] &= 0xffffffff;
		/* for >32 bit machines */
            }
        return seed;
    }

    /**
     * An alternative, more complete, method of seeding the
     * pseudo random number generator.  array must be an
     * array of 624 ints, and they can be any value as long as
     * they're not *all* zero.
     */

    private void setSeed(final int[] array) {
        int i, j, k;
        initSeed(19650218);
        i=1; j=0;
        k = (N>array.length ? N : array.length);
        for (; k!=0; k--) {
            mt[i] = (mt[i] ^ ((mt[i-1] ^ (mt[i-1] >>> 30)) * 1664525)) + array[j] + j; /* non linear */
            mt[i] &= 0xffffffff; /* for WORDSIZE > 32 machines */
            i++;
            j++;
            if (i>=N) { mt[0] = mt[N-1]; i=1; }
            if (j>=array.length) j=0;
	}
        for (k=N-1; k!=0; k--) {
            mt[i] = (mt[i] ^ ((mt[i-1] ^ (mt[i-1] >>> 30)) * 1566083941)) - i; /* non linear */
            mt[i] &= 0xffffffff; /* for WORDSIZE > 32 machines */
            i++;
            if (i>=N) {
                mt[0] = mt[N-1]; i=1;
	    }
	}
        mt[0] = 0x80000000; /* MSB is 1; assuring non-zero initial array */
    }

    /**
     * Generates a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1),
     * using the RanMT approach
     * @return a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1)
     */
    public double next() {
	int y;
	int z;

	if (mti >= N) {   // generate N words at one time

	    int kk;

	    for (kk = 0; kk < N - M; kk++) {
		y = (mt[kk] & UPPER_MASK) | (mt[kk+1] & LOWER_MASK);
		mt[kk] = mt[kk+M] ^ (y >>> 1) ^ mag01[y & 0x1];
	    }
	    for (; kk < N-1; kk++) {
		y = (mt[kk] & UPPER_MASK) | (mt[kk+1] & LOWER_MASK);
		mt[kk] = mt[kk+(M-N)] ^ (y >>> 1) ^ mag01[y & 0x1];
	    }
	    y = (mt[N-1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
	    mt[N-1] = mt[M-1] ^ (y >>> 1) ^ mag01[y & 0x1];

	    mti = 0;
	}

	y = mt[mti++];
	y ^= y >>> 11;                          // TEMPERING_SHIFT_U(y)
	y ^= (y << 7) & TEMPERING_MASK_B;       // TEMPERING_SHIFT_S(y)
	y ^= (y << 15) & TEMPERING_MASK_C;      // TEMPERING_SHIFT_T(y)
	y ^= (y >>> 18);                        // TEMPERING_SHIFT_L(y)

	if (mti >= N) {  // generate N words at one time
	    int kk;

	    for (kk = 0; kk < N - M; kk++) {
		z = (mt[kk] & UPPER_MASK) | (mt[kk+1] & LOWER_MASK);
		mt[kk] = mt[kk+M] ^ (z >>> 1) ^ mag01[z & 0x1];
	    }
	    for (; kk < N-1; kk++) {
		z = (mt[kk] & UPPER_MASK) | (mt[kk+1] & LOWER_MASK);
		mt[kk] = mt[kk+(M-N)] ^ (z >>> 1) ^ mag01[z & 0x1];
	    }
	    z = (mt[N-1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
	    mt[N-1] = mt[M-1] ^ (z >>> 1) ^ mag01[z & 0x1];

	    mti = 0;
	}

	z = mt[mti++];
	z ^= z >>> 11;                          // TEMPERING_SHIFT_U(z)
	z ^= (z << 7) & TEMPERING_MASK_B;       // TEMPERING_SHIFT_S(z)
	z ^= (z << 15) & TEMPERING_MASK_C;      // TEMPERING_SHIFT_T(z)
	z ^= (z >>> 18);                        // TEMPERING_SHIFT_L(z)

	/* derived from nextDouble documentation in jdk 1.2 docs, see top */
	return ((((long)(y >>> 6)) << 27) + (z >>> 5)) / (double)(1L << 53);
    }    
}