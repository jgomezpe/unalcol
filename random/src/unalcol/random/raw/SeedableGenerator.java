package unalcol.random.raw;

import java.util.*;

//
// Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
// https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/random/
//
/**
 *
 * Seedable Generator (raw generators using a seed) of Uniform Distributed pseudo Random Numbers in the interval [0.0,1.0) (x~U[0,1)). It is based on the RangPack definition of seedable
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/random/raw/SeedableGenerator.java">
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
public abstract class SeedableGenerator extends RawGenerator {

    /**
     * Generators Seed
     */
    protected long seed;

    /**
     * Creates a seedable generator with a time dependent seed (using the current time)
     */
    public SeedableGenerator() {
        this.initSeed();
    }

    /**
     * Creates a seedable generator with the given time dependent seed
     * @param seed The time information used for defining the seed
     */
    public SeedableGenerator(Date seed) {
        this.initSeed(seed.getTime());
    }

    /**
     * Creates a seedable generator with the given seed
     * @param seed The seed
     */
    public SeedableGenerator(long seed) {
        this.initSeed(seed);
    }

    /**
     *
     * Returns a seed calculated from the current date.
     * @return a long integer seed
     *
     */
    public long initSeed() {
        return this.initSeed((new Date()).getTime());
    }

    /**
     *
     * Returns a seed calculated from a given date.
     * @param seed Time information used for determining the seed
     * @return a long integer seed
     *
     */
    public long initSeed(Date seed) {
        return this.initSeed(seed.getTime());
    }

    /**
     *
     * Returns a seed calculated from a given seed
     * @param seed The seed
     * @return a long integer seed (actually, returns the given seed) 
     *
     */
    public long initSeed(long seed) {
        this.seed = seed;
        return seed;
    }

    /**
     * Returns the seed used by the generator
     * @return Seed used by the generator
     */
    public long getSeed() {
        return seed;
    }
}
