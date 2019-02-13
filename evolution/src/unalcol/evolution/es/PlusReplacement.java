/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.search.selection.Selection;
import unalcol.object.Tagged;

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
    public Tagged<T>[] pool(Tagged<T>[] current, Tagged<T>[] next){
    	@SuppressWarnings("unchecked")
		Tagged<T>[] ns = (Tagged<T>[])new Tagged[current.length+next.length];
    	System.arraycopy(current, 0, ns, 0, current.length);
    	System.arraycopy(next, 0, ns, current.length, next.length);
        return ns;
    }    
}