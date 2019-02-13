package unalcol.search.population;

import unalcol.descriptors.Descriptors;
import unalcol.search.BasicGoalBased;
import unalcol.search.Goal;
import unalcol.object.Tagged;
import unalcol.real.Array;

public class PopulationDescriptors<T> extends BasicGoalBased<T,Double> implements Descriptors{
	public double[] descriptors( T[] pop ) {
		Goal<T,Double> goal = goal();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		return Array.statistics_with_median(quality).get();		
	}

	public double[] descriptors(Tagged<T>[] pop) {
		Goal<T,Double> goal = goal();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		return Array.statistics_with_median(quality).get();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public double[] descriptors(Object obj) {
		Class<?> cl = obj.getClass().getComponentType();
		if( Tagged.class.isAssignableFrom(cl)) return descriptors((Tagged<T>[])obj); 
		return descriptors((T[])obj); 
	}
}