package unalcol.search.population;

import unalcol.search.RealValuedGoal;
import unalcol.sort.Order;
import unalcol.object.Tagged;

public class RealValuedPopulationGoal<T>{
	
	public Tagged<T> pick(Tagged<T>[] pop, RealValuedGoal<T> goal) {
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
