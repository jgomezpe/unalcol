package unalcol.search.population;

import unalcol.descriptors.Descriptors;
import unalcol.types.real.StatisticsWithMedian;
import unalcol.types.real.array.DoubleArray;

public class PopulationSolutionDescriptors<T> extends Descriptors<PopulationSolution<T>> {

	@Override
	public double[] descriptors(PopulationSolution<T> pop) {
		StatisticsWithMedian stat = DoubleArray.statistics_with_median(pop.quality());
		return stat.get();
	}

}
