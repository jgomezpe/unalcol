package unalcol.search.variation;

import unalcol.search.Goal;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;

public class VariationSelection_n_1<T> extends Variation_n_1<T>{
	protected Variation<T> variation;
	protected Selection<T> selection;

	public VariationSelection_n_1( Variation<T> variation, Selection<T> selection ){
		this.variation = variation;
		this.selection = selection;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    protected Solution<T> build(Solution<T>... parents ){
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