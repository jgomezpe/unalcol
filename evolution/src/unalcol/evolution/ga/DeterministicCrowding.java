package unalcol.evolution.ga;

import unalcol.search.population.PopulationReplacement;
import unalcol.search.population.PopulationSolution;
import unalcol.math.metric.QuasiMetric;
import unalcol.types.collection.vector.*;
import unalcol.clone.*;

/**
 * <p>Title: DeterministicCrowding</p>
 * <p>Description: The Deterministic Crowding Approach proposed by Mahfoud for niching</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * <p>Company: Prion</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class DeterministicCrowding<T> implements PopulationReplacement<T>{
	/**
	 * Distance between individuals
	 **/
	QuasiMetric<T> metric = null;

	/**
	 * Creates a Simple Genetic Algorithm Transformation function, with the given
	 * selection scheme, and simple crossover, and simple gen mutation operators. The
	 * operators have the given probability to be applied
	 * @param _metric Distance between individuals
	 */
	public DeterministicCrowding( QuasiMetric<T> metric ) {
		this.metric = metric;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PopulationSolution<T> apply(PopulationSolution<T> current, PopulationSolution<T> next) {
		int n=2*((current.size()+1)/2); 
		Vector<T> buffer = new Vector<>();
		double[] q = new double[n];
		for( int i=0; i<current.size(); i+=2 ){
			T P1, P2, C1, C2;
			int k = i;
			int j = (i+1)%current.size();
			P1 = current.value(i);
			P2 = current.value(j);
			C1 = next.value(i);
			C2 = next.value(j);
			if( metric.apply(P1, C1) + metric.apply(P2, C2) <=	metric.apply(P1, C2) + metric.apply(P2, C1)) {
		          k = j;
		          j = i;
			}
			if( next.quality(k) < current.quality(i) ){
				C1 = (T)Clone.create(P1);
				q[i] = current.quality(i);
			}else{
				q[i] = next.quality(k);
			}
			if( next.quality(j) < current.quality((i+1)%current.size()) ){
				C2 = (T)Clone.create(P2);	
				q[i] = current.quality((i+1)%current.size());
			}else{
				q[i+1] = next.quality(j);
			}
			
			buffer.add(C1);
			buffer.add(C2);
		}
		return new PopulationSolution<T>(buffer, q);
	}
}
