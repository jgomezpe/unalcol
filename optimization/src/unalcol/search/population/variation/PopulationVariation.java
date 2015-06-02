/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population.variation;

import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class PopulationVariation<T> {
  /**
   * Return the genetic operator arity (number of genomes required by the genetic
   * operator for producing new genomes
   * @return the genetic operator arity, <i>0</i> if the arity of the operator depends on the population
   */
    public int arity(){ return 0; };

    public abstract Vector<T> apply(Vector<T> pop);

    public Vector<T> apply(Space<T> space, Vector<T> pop){
        return space.repair(apply(pop));
    }
  
    public void adapt( double productivity ){};        
}
