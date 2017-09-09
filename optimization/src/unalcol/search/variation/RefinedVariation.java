package unalcol.search.variation;

import unalcol.Tagged;
import unalcol.services.MicroService;

public class RefinedVariation<T> extends MicroService<T> implements Variation<T> {
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
	public Tagged<T>[] apply(Tagged<T>... pop) {
		return refining.apply(refined.apply(pop));
	}

	public int arity(){ return refined.arity(); }		
}