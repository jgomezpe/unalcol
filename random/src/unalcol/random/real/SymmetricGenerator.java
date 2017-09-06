/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.RandomGenerator;
import unalcol.random.UsesRandomGenerator;
import unalcol.random.raw.UsesRawGenerator;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class SymmetricGenerator extends Tags implements TaggedCallerNamePair<Object>, UsesRawGenerator<Object>, UsesRandomGenerator<Double>, RandDouble {
   /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public Double next() {
	Object caller = caller();
	RandomGenerator<Double> g = getRandomGenerator(caller);
        return getRawGenerator(caller).bool()?g.next():-g.next();
    }     
    
    /*@Override
    public DoubleGenerator new_instance(){
        return new SymmetricGenerator(g.new_instance(), b.new_instance());
    }*/        
}