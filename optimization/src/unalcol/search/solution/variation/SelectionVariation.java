package unalcol.search.solution.variation;

import unalcol.search.Goal;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;

public class SelectionVariation<T> implements SolutionOperator<T>{
	protected int lambda;
	protected SolutionOperator<T> variation;
	protected Selection<T> selection;

	public SelectionVariation( int lambda, SolutionOperator<T> variation, Selection<T> selection ){
		this.lambda = lambda;
		this.variation = variation;
		this.selection = selection;
	}
	
    @SuppressWarnings("unchecked")
	public Solution<T>[] apply( Solution<T>... pop ){
    	String gName = Goal.class.getName();
    	Object goal = pop[0].data(gName);
    	Solution<T>[] children = variation.apply(pop);
    	for( Solution<T> c : children ){
    		c.set(gName, goal);
    	}
        return selection.pick(lambda, children);
    }
}