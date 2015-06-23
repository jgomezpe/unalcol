package unalcol.search.population.variation;

import unalcol.search.space.ArityOne;
import unalcol.types.collection.vector.Vector;

public class RefineOperator<T> extends Operator<T> {
	protected ArityOne<T> refining;
	protected Operator<T> refined;
	
	public RefineOperator( Operator<T> refined, ArityOne<T> refining ) {
		this.refined = refined;
		this.refining = refining;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Vector<T> apply(T... pop) {
		return refining.apply(refined.apply(pop));
	}

}
