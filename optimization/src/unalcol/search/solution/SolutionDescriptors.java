package unalcol.search.solution;

import unalcol.Tagged;
import unalcol.descriptors.Descriptors;
import unalcol.search.Goal;
import unalcol.services.MicroService;

public class SolutionDescriptors<T> extends MicroService<Tagged<T>> implements Descriptors<Tagged<T>> {
	protected Goal<T,Double> goal;

	public SolutionDescriptors( Goal<T,Double> goal ) { this.goal = goal; }

	@Override
	public double[] descriptors(){
		return new double[]{goal.apply(caller())};
	}
}
