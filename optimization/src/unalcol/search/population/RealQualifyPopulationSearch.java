package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.RealQualityGoal;
import unalcol.search.solution.Solution;
import unalcol.sort.Order;

public interface RealQualifyPopulationSearch<T> extends PopulationSearch<T, Double> {
	@Override
	public default Solution<T> pick(Population<T> pop) {
		String gName = Goal.class.getName();
		@SuppressWarnings("unchecked")
		RealQualityGoal<T> goal = (RealQualityGoal<T>)pop.data(gName);
		Order<Double> order = goal.order();
		int k=0;
		double q = (Double)pop.get(0).info(gName);
		for( int i=1; i<pop.size(); i++ ){
			double qi = (Double)pop.get(i).info(gName);
			if( order.compare(q, qi) < 0 ){
				k=i;
				q=qi;
			}
		}
		return pop.get(k);
	}
}
