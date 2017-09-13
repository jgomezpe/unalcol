package unalcol.search.population;

import unalcol.Tagged;
import unalcol.descriptors.Descriptors;
import unalcol.search.Goal;
import unalcol.search.GoalBased;
import unalcol.services.MicroService;
import unalcol.types.real.array.DoubleArray;

public class PopulationDescriptors<T> extends MicroService<Tagged<T>[]> implements GoalBased<T,Double>, Descriptors<Tagged<T>[]> {
	@Override
	public double[] descriptors() {
		Goal<T,Double> goal = goal();
		Tagged<T>[] pop = caller();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		return DoubleArray.statistics_with_median(quality).get();		
	}
}