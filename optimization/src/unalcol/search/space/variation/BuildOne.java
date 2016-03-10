package unalcol.search.space.variation;

import unalcol.search.solution.Solution;
import unalcol.search.variation.BuildOneSearchOperator;

public interface BuildOne<T> extends BuildOneSearchOperator<T> {
	@SuppressWarnings("unchecked")
	public default Solution<T> build( Solution<T>... pop ){
		Solution<T> s = new Solution<T>(build(get(pop)));
		s.cloneTaggedMethods(pop[0]);
		return s;
	}
}