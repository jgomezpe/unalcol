package unalcol.search.population.variation;

import unalcol.search.Goal;
import unalcol.search.population.PopulationSolution;
import unalcol.search.selection.Selection;
import unalcol.types.collection.vector.Vector;

public class GenericBuildOne<T> extends BuildOne<T>{
	protected Operator<T> variation;
	protected Goal<T> goal;
	protected Selection<T> selection;

	public GenericBuildOne( Operator<T> variation, Goal<T> goal,
										  Selection<T> selection ) {
		this.variation = variation;
		this.goal = goal;
		this.selection = selection;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public T build(T... parents ){
    	Vector<T> children = variation.apply(parents);
        PopulationSolution<T> solutions =  new PopulationSolution<T>(children, goal.quality(children), selection);
        return solutions.pick().value();
    }	
}
