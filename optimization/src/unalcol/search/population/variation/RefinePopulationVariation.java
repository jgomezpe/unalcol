package unalcol.search.population.variation;

import unalcol.search.space.Variation;
import unalcol.types.collection.vector.Vector;

public class RefinePopulationVariation<T> extends PopulationVariation<T> {
	protected Variation<T> refining;
	protected PopulationVariation<T> refined;
	
	public RefinePopulationVariation( PopulationVariation<T> refined, Variation<T> refining ) {
		this.refined = refined;
		this.refining = refining;
	}
	
	@Override
	public Vector<T> apply(Vector<T> pop) {
		return refining.apply(refined.apply(pop));
	}

}
