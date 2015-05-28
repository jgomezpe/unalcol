/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public interface PopulationTransformation<T> {
    public void apply(  Vector<T> population, Vector<Double> quality, 
                        Space<T> space, Goal<T> goal);    
}