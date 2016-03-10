package unalcol.search.solution.variation;

import unalcol.search.Goal;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;

public class SelectionBuildOneSolution<T> extends BuildOneSolution<T>{
	protected SolutionOperator<T> variation;
	protected Selection<T> selection;

	public SelectionBuildOneSolution( SolutionOperator<T> variation, Selection<T> selection ){
		this.variation = variation;
		this.selection = selection;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public Solution<T> build(Solution<T>... parents ){
    	String gName = Goal.class.getName();
    	Object goal = parents[0].data(gName);
    	Solution<T>[] children = variation.apply(parents);
    	for( Solution<T> c : children ){
    		c.set(gName, goal);
    	}
        int index = selection.choose_one(children);
        return children[index];
    }
}