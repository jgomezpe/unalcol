/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.search.population.Population;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;

/**
 *
 * @author jgomez
 */
public class PlusReplacement<T> extends ESReplacement<T> {
    public PlusReplacement( int mu ) {
        super(mu);
    }
    
    public PlusReplacement( int mu, Selection<T> sel ) {
        super(mu, sel);
    }
    
    @Override
    public Population<T> pool(Population<T> current, Population<T> next){
    	@SuppressWarnings("unchecked")
		Solution<T>[] ns = (Solution<T>[])new Solution[current.size()+next.size()];
    	System.arraycopy(current.object(), 0, ns, 0, current.size());
    	System.arraycopy(next.object(), current.size(), ns, current.size(), next.size());
        return new Population<T>(ns);
    }    
}