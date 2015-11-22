/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.search.selection.Selection;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class CommaReplacement<T,P>  implements ESReplacement<T,P> {
    public CommaReplacement( int _mu ) {
        super(_mu);
    }
    
    public CommaReplacement( int _mu, Selection<T> sel ) {
        super(_mu, sel);
    }
    
    @Override
    public Vector<Solution<T>> pool(Vector<Solution<T>> parents, Vector<Solution<T>> offspring){
        return offspring;
    }
}    
