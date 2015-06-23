package unalcol.search.population.variation;

import unalcol.types.collection.vector.Vector;

public abstract class BuildOne<T> extends Operator<T> {
	@SuppressWarnings("unchecked")
	public abstract T build( T... pop );

	@SuppressWarnings("unchecked")
	@Override
	public Vector<T> apply(T... pop) {
		Vector<T> newPop = new Vector<T>();
		newPop.add(build(pop));
		return newPop;
	}

}
