/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.optimization.replacement.*;
import unalcol.optimization.selection.Elitism;
import unalcol.optimization.selection.Selection;
import unalcol.optimization.solution.Solution;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class ESReplacement<T> extends Replacement<T>{
    protected int mu;
    protected Selection<T> selection;

    public ESReplacement( int _mu ) {
        this.selection = new Elitism(1.0, 0.1);
        this.mu = _mu;
    }
    
    public ESReplacement( int _mu, Selection<T> sel ) {
        this.selection = sel;
        this.mu = _mu;
    }
    
    public abstract Vector<Solution<T>> pool(Vector<Solution<T>> parents, Vector<Solution<T>> offspring);

    @Override
    public Vector<Solution<T>> apply(Vector<Solution<T>> parents, Vector<Solution<T>> offspring) {
        return selection.apply(mu, pool(parents,offspring));
    }
    
}
