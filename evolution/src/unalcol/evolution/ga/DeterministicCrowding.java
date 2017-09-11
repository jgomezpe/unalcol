package unalcol.evolution.ga;

import unalcol.search.population.PopulationReplacement;
import unalcol.sort.Order;
import unalcol.search.GoalBased;
import unalcol.search.RealQualityGoal;
import unalcol.Tagged;
import unalcol.Thing;
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

public class DeterministicCrowding<T> extends Thing implements GoalBased<T,Double>, PopulationReplacement<T>{
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
	public Tagged<T>[] apply(Tagged<T>[] current, Tagged<T>[] next){
		RealQualityGoal<T> goal = (RealQualityGoal<T>)goal();
		Order<Double> order = goal.order();
		Tagged<T>[] buffer = (Tagged<T>[])new Tagged[current.length];
		for( int i=0; i<current.length; i+=2 ){
			Tagged<T> P1, P2, C1, C2;
			P1 = current[i];
			P2 = current[i+1];
			C1 = next[i];
			C2 = next[i+1];
			if( metric.apply(P1.unwrap(), C1.unwrap()) + metric.apply(P2.unwrap(), C2.unwrap()) <=	
				metric.apply(P1.unwrap(), C2.unwrap()) + metric.apply(P2.unwrap(), C1.unwrap())) {
				Tagged<T> t = C1;
				C1 = C2;
				C2 = t;
			}
			if( order.compare(goal.apply(C1), goal.apply(P1)) < 0 )
				C1 = P1;
			if( order.compare(goal.apply(C2), goal.apply(P2)) < 0  )
				C2 = P2;
			
			buffer[i] = C1;
			buffer[i+1] = C2;
		}
		return buffer;
	}
}