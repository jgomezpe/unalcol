/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic;

import unalcol.math.logic.Predicate;

/**
 *
 * @author jgomez
 */
public abstract class DynamicSystem {
    public abstract void step();
    
    public void simulate( Predicate<DynamicSystem> predicate ){
        do{
            step();
        }while( predicate.evaluate(this) );
    }    
}
