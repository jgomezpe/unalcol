/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.es;

import unalcol.search.population.PopulationSolution;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public interface ESReplacement<T,P>{
    public void apply( PopulationSolution<T> parent, Vector<P> p_parameters,
    		           PopulationSolution<T> children, Vector<P> c_parameters,
    		           PopulationSolution<T> finalPop, Vector<P> finalParm);    
}
