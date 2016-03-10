package unalcol.search.solution;

import unalcol.descriptors.Descriptors;
import unalcol.search.Goal;

public class SolutionDescriptors<T> extends Descriptors<Solution<T>> {

	@Override
	public double[] descriptors(Solution<T> sol) {
		return new double[]{(Double)sol.info(Goal.class.getName())};
	}
}
