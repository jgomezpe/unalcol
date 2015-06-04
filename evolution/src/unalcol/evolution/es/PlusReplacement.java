/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.optimization.selection.Selection;
import unalcol.optimization.solution.Solution;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class PlusReplacement<T> extends ESReplacement<T> {
    public PlusReplacement( int _mu ) {
        super(_mu);
    }
    
    public PlusReplacement( int _mu, Selection<T> sel ) {
        super(_mu, sel);
    }
    
    @Override
    public Vector<Solution<T>> pool(Vector<Solution<T>> parents, Vector<Solution<T>> offspring){
        for( int i=0; i<parents.size(); i++ ){
            offspring.add(parents.get(i));
        }
        return offspring;
    }    
}
