package unalcol.search.population;

import unalcol.search.RealValuedGoal;
import unalcol.sort.Order;
import unalcol.types.object.tagged.Tagged;

public interface RealBasedPopulationSearch<T> extends PopulationSearch<T, Double> {
	@Override
	public default Tagged<T> pick(Tagged<T>[] pop) {
		RealValuedGoal<T> goal = (RealValuedGoal<T>)goal();
		Order order = goal.order();
		int k=0;
		double q = goal.apply(pop[0]);
		for( int i=1; i<pop.length; i++ ){
			double qi = goal.apply(pop[i]);
			if( order.compare(q, qi) < 0 ){
				k=i;
				q=qi;
			}
		}
		return pop[k];
	}
}
