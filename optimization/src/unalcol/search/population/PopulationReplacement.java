/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Jonatan
 */
public abstract class PopulationReplacement<T> {
    public PopulationSolution<T> apply( Vector<T> current, double[] qc, Vector<T> next, double[] qn ){
    	return apply( new PopulationSolution<T>(current, qc), new PopulationSolution<T>(next, qn) );
    }

    public abstract PopulationSolution<T> apply( PopulationSolution<T> current, PopulationSolution<T> next );
}
