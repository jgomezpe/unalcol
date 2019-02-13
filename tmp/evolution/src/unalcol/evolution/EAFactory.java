package unalcol.evolution;

import unalcol.math.function.iterative.ForLoopCondition;
import unalcol.evolution.ga.GAStep;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Selection;
import unalcol.search.selection.Tournament;
import unalcol.search.variation.Variation_1_1;
import unalcol.search.variation.Variation_2_2;
import unalcol.object.Tagged;

public class EAFactory<T> {
	//Generational Genetic Algorithm factory (Only uses offsprings in replacement)
	
	public PopulationSearch<T,Double>	generational_ga(
				int mu, Selection<T> parent_selection, 
				Variation_1_1<T> mutation, 
				Variation_2_2<T> xover, double xover_probability, 
				Predicate<Tagged<T>[]> tC ){
		return new IterativePopulationSearch<T,Double>(
						new GAStep<T>( mu, parent_selection, mutation, xover, xover_probability, true),
						tC );
	}

	public PopulationSearch<T,Double>	generational_ga(
			int mu, Selection<T> parent_selection, 
			Variation_1_1<T> mutation, 
			Variation_2_2<T> xover, double xover_probability, 
			int MAXITERS ){
		return generational_ga(
					mu, parent_selection, mutation, xover, xover_probability,
					new ForLoopCondition<Tagged<T>[]>(MAXITERS) );
	}
	
	public PopulationSearch<T,Double>	generational_ga(
			int mu, Goal<T,Double> goal,
			Variation_1_1<T> mutation, 
			Variation_2_2<T> xover, double xover_probability, 
			int MAXITERS ){
		return generational_ga(
					mu, new Tournament<T,Double>(goal, 4), mutation, xover, xover_probability,
					MAXITERS );
	}
	
	//Steady State Genetic Algorithm factory (Uses parents and offsprings in replacement)

	public PopulationSearch<T,Double>	steady_ga(
			int mu, Selection<T> parent_selection, 
			Variation_1_1<T> mutation, 
			Variation_2_2<T> xover, double xover_probability, 
			Predicate<Tagged<T>[]> tC ){
		return new IterativePopulationSearch<T,Double>(
						new GAStep<T>( mu, parent_selection, mutation, xover, xover_probability, false),
						tC );
	}
	
	public PopulationSearch<T,Double>	steady_ga(
			int mu, Selection<T> parent_selection, 
			Variation_1_1<T> mutation, 
			Variation_2_2<T> xover, double xover_probability, 
			int MAXITERS ){
		return steady_ga(
					mu, parent_selection, mutation, xover, xover_probability,
					new ForLoopCondition<Tagged<T>[]>(MAXITERS) );
	}
	
	public PopulationSearch<T,Double>	steady_ga(
			int mu, Goal<T,Double> goal,
			Variation_1_1<T> mutation, 
			Variation_2_2<T> xover, double xover_probability, 
			int MAXITERS ){
		return steady_ga(
					mu, new Tournament<T,Double>(goal, 4), mutation, xover, xover_probability,
					MAXITERS );
	}	
	
	// HAEA Factory
	
	public PopulationSearch<T,Double> HAEA( HaeaStep<T> step, Predicate<Tagged<T>[]> tC ){
		return new IterativePopulationSearch<T,Double>( step, tC );
	}

	public PopulationSearch<T,Double> HAEA( int mu, HaeaOperators<T> operators, Selection<T> selection, Predicate<Tagged<T>[]> tC ){
		return HAEA( new HaeaStep<T>(mu,selection,operators), tC );
	}

	public PopulationSearch<T,Double> HAEA( int mu, HaeaOperators<T> operators, Selection<T> selection, int MAXITERS ){
		return HAEA( mu, operators, selection, new ForLoopCondition<Tagged<T>[]>(MAXITERS) );
	}

	public PopulationSearch<T,Double> HAEA( HaeaStep<T> step, int MAXITERS ){
		return HAEA( step, new ForLoopCondition<Tagged<T>[]>(MAXITERS) );
	}
	
		public PopulationSearch<T,Double> HAEA_Generational( int mu, HaeaOperators<T> operators, Selection<T> selection, int MAXITERS ){
		return HAEA( new HaeaStep<T>(mu,selection, operators, false), MAXITERS );
	}	
}