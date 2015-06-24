package unalcol.search;

import unalcol.descriptors.Descriptors;

public class SolutionDescriptors<T> extends Descriptors<Solution<T>> {

	@Override
	public double[] descriptors(Solution<T> sol) {
		return new double[]{sol.quality()};
	}
}
