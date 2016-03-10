package unalcol.search.variation;

import unalcol.search.solution.Solution;

public class RefineSearchOperator<T> implements SearchOperator<T> {
	protected ArityOneSearchOperator<T> refining;
	protected SearchOperator<T> refined;
	
	public RefineSearchOperator( SearchOperator<T> refined, ArityOneSearchOperator<T> refining ) {
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