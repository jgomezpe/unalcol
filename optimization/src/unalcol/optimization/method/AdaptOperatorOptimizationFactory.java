package unalcol.optimization.method;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.optimization.method.annealing.SimpleSimulatedAnnealingScheme;
import unalcol.optimization.method.annealing.SimulatedAnnealingReplacement;
import unalcol.optimization.method.annealing.SimulatedAnnealingScheme;
import unalcol.optimization.method.hillclimbing.HillClimbingReplacement;
import unalcol.search.local.AdaptOperatorLocalSearch;
import unalcol.search.local.AdaptSearchOperatorParameters;
import unalcol.search.local.IterativeLocalSearch;
import unalcol.search.local.LocalSearch;
import unalcol.search.solution.Solution;
import unalcol.search.variation.Variation_1_1;

public class AdaptOperatorOptimizationFactory<T,P> {
	// Adapt parameters - Hill Climbing
	
    public LocalSearch<T,Double>
		hill_climbing(	
			Variation_1_1<T> variation, 
			AdaptSearchOperatorParameters<P> adapt,
			HillClimbingReplacement<T> replace, 
			Predicate< Solution<T> > tC ){
    	return new IterativeLocalSearch<T, Double>(
    					new AdaptOperatorLocalSearch<T,P>(variation, adapt, replace), tC);
    }

    public LocalSearch<T,Double> 
    	hill_climbing(
 			Variation_1_1<T> variation, 
 			AdaptSearchOperatorParameters<P> adapt,
 			boolean neutral, int MAX_ITERS ){
    	return hill_climbing( variation, adapt, new HillClimbingReplacement<T>(neutral), new ForLoopCondition<Solution<T>>(MAX_ITERS));
    }

	 public LocalSearch<T,Double> 
	 	simulated_annealing(  
	 		Variation_1_1<T> variation, 
 			AdaptSearchOperatorParameters<P> adapt,
			SimulatedAnnealingReplacement<T> replace, 
			Predicate<Solution<T>> tC ){
		return new IterativeLocalSearch<T,Double>( 
				new AdaptOperatorLocalSearch<T,P>( variation, adapt, replace),  tC );
	 }
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing(
	 		Variation_1_1<T> variation, 
			AdaptSearchOperatorParameters<P> adapt,
			SimulatedAnnealingScheme scheme, int MAX_ITERS ){
		return simulated_annealing( variation, adapt, 
 			new SimulatedAnnealingReplacement<T>(scheme), 
 								new ForLoopCondition<Solution<T>>(MAX_ITERS) );
	 }       

	public LocalSearch<T,Double> 
	 	simulated_annealing(
	 		Variation_1_1<T> variation, 
 			AdaptSearchOperatorParameters<P> adapt,
			int K, int MAX_ITERS ){
		return simulated_annealing( variation, adapt, 
									new SimpleSimulatedAnnealingScheme(K), 
									MAX_ITERS );
	}       

	public LocalSearch<T,Double> simulated_annealing( 
 			Variation_1_1<T> variation, 
 			AdaptSearchOperatorParameters<P> adapt,
 			int MAX_ITERS ){
		return simulated_annealing( variation, adapt, MAX_ITERS, MAX_ITERS );
	}
}