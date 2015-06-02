package unalcol.search.population.variation;

import unalcol.types.collection.vector.Vector;

public abstract class BuildOne<T> extends PopulationVariation<T> {
	public abstract T build( Vector<T> pop );

	@Override
	public Vector<T> apply(Vector<T> pop) {
		Vector<T> newPop = new Vector<T>();
		newPop.add(build(pop));
		return newPop;
	}

}
