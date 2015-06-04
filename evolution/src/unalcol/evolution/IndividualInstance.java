/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution;

import unalcol.instance.InstanceProvider;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.solution.SolutionInstance;

/**
 *
 * @author jgomez
 */
public class IndividualInstance extends SolutionInstance{
    protected GrowingFunction grow;    
    public IndividualInstance( GrowingFunction grow ){
        this.grow = grow;
    }

    @Override
    public Solution get( Solution sol ) {
        Object genome = InstanceProvider.get(((Individual)sol).genome());
        return new Individual(genome, grow.get(genome));
    }

    @Override
    public Object owner() {
        return Individual.class;
    }    
}
