/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.util.RandBool;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class SymmetricGenerator implements DoubleGenerator {
    protected DoubleGenerator g;
    protected RandBool b = new RandBool();
    public SymmetricGenerator(DoubleGenerator _g){
        g = _g;
    }
    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public Double next() {
        return b.next()?g.next():-g.next();
    }     
    
    public SymmetricGenerator( DoubleGenerator _g, RandBool _b ){
        g = _g;
        b = _b;
    }
    
    /*@Override
    public DoubleGenerator new_instance(){
        return new SymmetricGenerator(g.new_instance(), b.new_instance());
    }*/        
}
