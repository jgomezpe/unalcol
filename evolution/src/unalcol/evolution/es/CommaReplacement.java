/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.search.population.Population;
import unalcol.search.selection.Selection;

/**
 *
 * @author jgomez
 */
public class CommaReplacement<T> extends ESReplacement<T> {
    public CommaReplacement( int mu ) {
        super( mu );
    }
    
    public CommaReplacement( int mu, Selection<T> sel ) {
        super(mu, sel);
    }
    
    @Override
    public Population<T> pool(Population<T> current, Population<T> next){
        return next;
    }
}    