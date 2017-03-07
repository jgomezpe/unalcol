package unalcol.random.raw;

import unalcol.service.ServiceCore;

// Unified Random generation Pack 1.0 by Jonatan Gomez-Perdomo
// https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * RawGenerator
 * <P> Abstract class. It is a generator of uniform distributed pseudo random numbers in the interval [0.0,1.0) (x~U[0,1)).
 *  It has some useful methods for generating boolean values and uniform distributed integers. 
 *
 * <P>
 * <A HREF="https://github.com/jgomezpe/unalcol/tree/master/src/unalcol/random/raw/RawGenerator.java">
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
public abstract class RawGenerator{
    /**
     * Generates a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1)
     * @return a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1)
     */
    public abstract double next();

    
    /**
     * Generates a boolean value (<i>false</i> or <i>true</i> with equal probability)
     * @return A boolean value (<i>false</i> or <i>true</i> with equal probability)
     */
    public boolean bool() {
        return (next() >= 0.5);
    }

    /**
     * Generates a boolean value with the given probability
     * @param falseProbability The probability of generating a <i>false</i>. Clearly, (1.0-falseProbability)
     * provides the probability of generating a <i>true</i> value
     * @return A boolean value with the given probability
     */
    public boolean bool(double falseProbability) {
        return (next() >= falseProbability);
    }

    /**
     * Generates a uniform distributed integer value in the interval [0,max-1]
     * @param max The superior limit of the half-open interval [0,max) defined for generating integer values
     * @return An integer value in the interval [0,max) with uniform distribution
     */
    public int integer(int max) {
        return ((int) (max * next()));
    }    
    
    /**
     * Returns a set of random numbers following the x ~ U[0,1) distribution
     * @param v Array used for returning the generated random numbers
     * @param offset Stating point for storing the generated real numbers
     * @param m The number of random numbers to be generated and returned in array <i>v</i>.
     */
    public void raw(double[] v, int offset, int m) {
        for (int i = 0; i < m; i++) {
            v[i+offset] = next();
        }
    }

    /**
     * Returns a set of random numbers following the x ~ U[0,1) distribution
     * @param m The number of random numbers to be generated
     * @return An array with <i>m</i> random numbers following the x ~ U[0,1) distribution
     */
    public double[] raw(int m) {
        double[] v = null;
        if (m > 0) {
            v = new double[m];
            raw(v, 0, m);
        }
        return v;
    }
    
    /**
     * Creates a new instance of the random number generator
     * @return A new instance of the random number generator
     */
    public abstract RawGenerator new_instance();
    
    /**
     * Generates a random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1), 
     * using the RawGenerator associated to the given object <i>obj</i>.
     * @param obj Owner of the RawGenerator that will be used for generating the real value. 
     * @return A random number in the interval [0.0,1.0) following a uniform distribution x~U[0,1).
     */
     public static double next( Object obj ){
        return get(obj).next();        
    }

    /**
     * Generates a boolean value (with same probability), using the RawGenerator associated to the given object <i>obj</i>
     * @param obj Owner of the RawGenerator that will be used for generating the boolean value. 
     * @return A boolean value.
     */
    public static boolean bool( Object obj ){
        return get(obj).bool();        
    }

    /**
     * Generates a boolean value with the given probability, using the RawGenerator associated to the given object <i>obj</i>.
     * @param obj Owner of the RawGenerator that will be used for generating the boolean value. 
     * @param falseProbability The probability of generating a <i>false</i>. Clearly, (1.0-falseProbability)
     * provides the probability of generating a <i>true</i> value
     * @return A boolean value with the given probability
     */
    public static boolean bool( Object obj, double falseProbability ){
        return get(obj).bool(falseProbability);        
    }

    /**
     * Generates a uniform distributed integer value in the interval [0,max-1], using the RawGenerator associated to the given object <i>obj</i>.
     * @param obj Owner of the RawGenerator that will be used for generating the boolean value. 
     * @param max The superior limit of the half-open interval [0,max) defined for generating integer values
     * @return An integer value in the interval [0,max) with uniform distribution
     */
    public static int integer( Object obj, int max ){
        return get(obj).integer(max);        
    }

    /**
     * Returns a set of random numbers following the x ~ U[0,1) distribution, using the RawGenerator associated to the given object <i>obj</i>.
     * @param obj Owner of the RawGenerator that will be used for generating the boolean value. 
     * @param v Array used for returning the generated random numbers
     * @param offset Stating point for storing the generated real numbers
     * @param m The number of random numbers to be generated and returned in array <i>v</i>.
     */
    public static void raw( Object obj, double[] v, int offset, int m ){
        get(obj).raw(v, offset, m);        
    }

    /**
     * Returns a set of random numbers following the x ~ U[0,1) distribution, using the RawGenerator associated to the given object <i>obj</i>.
     * @param obj Owner of the RawGenerator that will be used for generating the boolean value. 
     * @param m The number of random numbers to be generated
     * @return An array with <i>m</i> random numbers following the x ~ U[0,1) distribution
     */
    public static double[] raw( Object obj, int m ){
        return get(obj).raw(m);        
    }
    
    /**
     * Obtains the RawGenerator associated to object <i>owner</i>.
     * @param owner Owner of the RawGenerator that is being searched for.
     * @return The RawGenerator associated to object <i>owner</i>.
     */
    public static RawGenerator get( Object owner ){
        if( ServiceCore.get(Object.class, RawGenerator.class) == null )  set(Object.class, new JavaGenerator());
        return (RawGenerator)ServiceCore.get(owner, RawGenerator.class);
    }
 
    /**
     * Associates a RawGenerator (if possible) to an object.
     * @param owner Object that will have associated the RawGenerator.
     * @param raw The RawGenerator to be associated to object <i>owner</i>.
     * @return <i>true</i> if it was possible to associate <i>raw</i> as RawGenerator to object <i>owner</i>, <i>false</i> otherwise.
     */
    public static boolean set( Object owner, RawGenerator raw ){
    	return ServiceCore.set(owner, RawGenerator.class, raw);
    }   
}