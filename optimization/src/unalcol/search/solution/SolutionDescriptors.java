package unalcol.search.solution;

import unalcol.Tagged;
import unalcol.Thing;
import unalcol.descriptors.Descriptors;
import unalcol.search.Goal;

public class SolutionDescriptors<T,R> extends Thing implements Descriptors<Tagged<T>> {
	protected Goal<T,R> goal;

	public SolutionDescriptors( Goal<T,R> goal ) { this.goal = goal; }
	@Override
	public double[] descriptors(){
		return new double[]{(Double)sol.info(Goal.class.getName())};
	}
}
