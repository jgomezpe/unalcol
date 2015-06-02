package unalcol.search.population.variation;

import unalcol.search.Goal;
import unalcol.search.population.PopulationSolution;
import unalcol.search.selection.Selection;
import unalcol.types.collection.vector.Vector;

public class PopulationVariationToBuildOne<T> extends BuildOne<T>{
	protected PopulationVariation<T> variation;
	protected Goal<T> goal;
	protected Selection<T> selection;

	public PopulationVariationToBuildOne( PopulationVariation<T> variation, Goal<T> goal,
										  Selection<T> selection ) {
		this.variation = variation;
		this.goal = goal;
		this.selection = selection;
	}
	
    @Override
    public T build( Vector<T> parents ){
    	Vector<T> children = variation.apply(parents);
        PopulationSolution<T> solutions =  new PopulationSolution<T>(children, goal.quality(children), selection);
        return solutions.pick().value();
    }	
}
