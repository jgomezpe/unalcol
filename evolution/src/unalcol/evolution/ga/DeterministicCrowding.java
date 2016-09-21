package unalcol.evolution.ga;

import unalcol.search.population.PopulationReplacement;
import unalcol.search.solution.Solution;
import unalcol.sort.Order;
import unalcol.search.Goal;
import unalcol.search.RealQualityGoal;
import unalcol.search.population.Population;
import unalcol.math.metric.QuasiMetric;

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
	public Population<T> apply(Population<T> current, Population<T> next){
		String gName = Goal.class.getName();
		RealQualityGoal<T> goal = (RealQualityGoal<T>)current.data(gName);
		Order<Double> order = goal.order();
		Solution<T>[] buffer = new Solution[current.size()];
		for( int i=0; i<current.size(); i+=2 ){
			Solution<T> P1, P2, C1, C2;
			P1 = current.get(i);
			P2 = current.get(i+1);
			C1 = next.get(i);
			C2 = next.get(i+1);
			if( metric.apply(P1.object(), C1.object()) + metric.apply(P2.object(), C2.object()) <=	
				metric.apply(P1.object(), C2.object()) + metric.apply(P2.object(), C1.object())) {
				Solution<T> t = C1;
				C1 = C2;
				C2 = t;
			}
			if( order.compare((Double)C1.info(gName), (Double)P1.info(gName)) < 0 )
				C1 = P1;
			if( order.compare((Double)C2.info(gName), (Double)P2.info(gName)) < 0  )
				C2 = P2;
			
			buffer[i] = C1;
			buffer[i+1] = C2;
		}
		return new Population<T>(buffer);
	}
}