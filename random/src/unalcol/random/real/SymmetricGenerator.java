/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.util.RandBool;

/**
 *
 * @author jgomez
 */
public class SymmetricGenerator extends DoubleGenerator {
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
    public double generate() {
        return b.next()?g.generate():-g.generate();
    }     
    
    public SymmetricGenerator( DoubleGenerator _g, RandBool _b ){
        g = _g;
        b = _b;
    }
    
    @Override
    public DoubleGenerator new_instance(){
        return new SymmetricGenerator(g.new_instance(), b.new_instance());
    }        
}
