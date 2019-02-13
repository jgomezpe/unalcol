package unalcol.optimization.method;

import unalcol.math.function.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.optimization.method.annealing.SimpleSimulatedAnnealingScheme;
import unalcol.optimization.method.annealing.SimulatedAnnealingReplacement;
import unalcol.optimization.method.annealing.SimulatedAnnealingScheme;
import unalcol.optimization.method.hillclimbing.HillClimbingReplacement;
import unalcol.search.Goal;
import unalcol.search.local.IterativeLocalSearch;
import unalcol.search.local.LocalSearch;
import unalcol.search.local.VariationReplaceLocalSearch;
import unalcol.search.variation.Variation_1_1;
import unalcol.object.Tagged;

public class OptimizationFactory<T> {
	public LocalSearch<T,Double> 
		hill_climbing( Variation_1_1<T> variation, HillClimbingReplacement<T> replace, Predicate<Tagged<T>> tC ){
		return new IterativeLocalSearch<T, Double>(new VariationReplaceLocalSearch<T>(variation, replace), tC);
	}

	 public LocalSearch<T,Double> 
		hill_climbing( Goal<T,Double> goal, Variation_1_1<T> variation,	boolean neutral, int MAX_ITERS ){
		 HillClimbingReplacement<T> replacement = new HillClimbingReplacement<T>( neutral );
		 replacement.setGoal(goal);
		 return hill_climbing( variation, replacement, new ForLoopCondition<Tagged<T>>(MAX_ITERS) );
	 }  
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing( Variation_1_1<T> variation, SimulatedAnnealingReplacement<T> replace, Predicate<Tagged<T>> tC ){
		return new IterativeLocalSearch<T,Double>( 
				new VariationReplaceLocalSearch<T>( variation, replace),  tC );
	 }
	 
	 public LocalSearch<T,Double> 
	 	simulated_annealing( Goal<T,Double> goal, Variation_1_1<T> variation, SimulatedAnnealingScheme scheme, int MAX_ITERS ){
		 SimulatedAnnealingReplacement<T> replacement = new SimulatedAnnealingReplacement<T>(scheme);
		 replacement.setGoal(goal);
    	return simulated_annealing( variation, replacement, 
    								new ForLoopCondition<Tagged<T>>(MAX_ITERS) );
    }       

	public LocalSearch<T,Double> 
	 	simulated_annealing( Goal<T,Double> goal, Variation_1_1<T> variation, int K, int MAX_ITERS ){
		return simulated_annealing( goal, variation, new SimpleSimulatedAnnealingScheme(K), MAX_ITERS );
	}       

    public LocalSearch<T,Double> simulated_annealing( Goal<T,Double> goal, Variation_1_1<T> variation, int MAX_ITERS ){
    	return simulated_annealing( goal, variation, MAX_ITERS, MAX_ITERS );
    }
}