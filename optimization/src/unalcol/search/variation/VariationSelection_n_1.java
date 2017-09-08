package unalcol.search.variation;

import unalcol.Tagged;
import unalcol.search.Goal;
import unalcol.search.selection.Selection;

public class VariationSelection_n_1<T> implements Variation_n_1<T>{
	protected Variation<T> variation;
	protected Selection<T> selection;

	public VariationSelection_n_1( Variation<T> variation, Selection<T> selection ){
		this.variation = variation;
		this.selection = selection;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Tagged<T> build(Tagged<T>... parents ){
		String gName = Goal.class.getName();
		Goal<T, ?> goal = (Goal<T,?>)parents[0].get(gName);
		Tagged<T>[] children = variation.apply(parents);
		goal.array_apply(children);
		for( Tagged<T> c : children ){ c.put(gName, goal); }
		int index = selection.choose_one(children);
		return children[index];
	}
}