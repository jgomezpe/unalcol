package unalcol.optimization.method;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.optimization.method.annealing.SimpleSimulatedAnnealingScheme;
import unalcol.optimization.method.annealing.SimulatedAnnealingReplacement;
import unalcol.optimization.method.annealing.SimulatedAnnealingScheme;
import unalcol.optimization.method.hillclimbing.HillClimbingReplacement;
import unalcol.search.local.IterativeLocalSearch;
import unalcol.search.local.LocalSearch;
import unalcol.search.local.VariationReplaceLocalSearch;
import unalcol.search.solution.Solution;
import unalcol.search.variation.Variation_1_1;

public class OptimizationFactory<T> {
	public LocalSearch<T,Double> 
		hill_climbing(
			Variation_1_1<T> variation, 
			HillClimbingReplacement<T> replace, 
			Predicate<Solution<T>> tC ){
		return new IterativeLocalSearch<T, Double>(new VariationReplaceLocalSearch<T>(variation, replace), tC);
	}

	 public LocalSearch<T,Double> 
		hill_climbing(
			Variation_1_1<T> variation, 
	 		boolean neutral, int MAX_ITERS ){
	 	return hill_climbing(	variation, 
	 							new HillClimbingReplacement<T>( neutral ),
	 							new ForLoopCondition<Solution<T>>(MAX_ITERS) );
	 }  
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing(  
	 		Variation_1_1<T> variation, 
			SimulatedAnnealingReplacement<T> replace, 
			Predicate<Solution<T>> tC ){
		return new IterativeLocalSearch<T,Double>( 
				new VariationReplaceLocalSearch<T>( variation, replace),  tC );
	 }
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing(
    		Variation_1_1<T> variation, 
   			SimulatedAnnealingScheme scheme, int MAX_ITERS ){
    	return simulated_annealing( variation, 
    			new SimulatedAnnealingReplacement<T>(scheme), 
    								new ForLoopCondition<Solution<T>>(MAX_ITERS) );
    }       

	public LocalSearch<T,Double> 
	 	simulated_annealing(
	 		Variation_1_1<T> variation, 
			int K, int MAX_ITERS ){
		return simulated_annealing( variation, 
									new SimpleSimulatedAnnealingScheme(K), 
									MAX_ITERS );
	}       

    public LocalSearch<T,Double> simulated_annealing( 
    			Variation_1_1<T> variation, int MAX_ITERS ){
    	return simulated_annealing( variation, MAX_ITERS, MAX_ITERS );
    }
}