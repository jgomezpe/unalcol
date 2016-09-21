package unalcol.search.variation;

import unalcol.search.solution.Solution;

public class RefinedVariation<T> extends Variation<T> {
	protected Variation_1_1<T> refining;
	protected Variation<T> refined;
	
	public RefinedVariation( Variation<T> refined, Variation_1_1<T> refining ) {
		this.refined = refined;
		this.refining = refining;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] apply(T... pop) {
		return refining.apply(refined.apply(pop));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Solution<T>[] apply(Solution<T>... pop) {
		return refining.apply(refined.apply(pop));
	}

	public int arity(){ return refined.arity(); }		
}