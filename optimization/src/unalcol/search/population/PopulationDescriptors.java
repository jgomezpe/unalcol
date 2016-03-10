package unalcol.search.population;

import unalcol.descriptors.Descriptors;
import unalcol.search.Goal;
import unalcol.types.real.array.DoubleArray;

public class PopulationDescriptors<T> extends Descriptors<Population<T>> {

	@Override
	public double[] descriptors(Population<T> pop) {
		String gName = Goal.class.getName();
		double[] quality = new double[pop.size()];
		for(int i=0; i<quality.length; i++ ){
			quality[i] = (Double)pop.get(i).info(gName);
		} 
		return DoubleArray.statistics_with_median(quality).get();		
	}

}
