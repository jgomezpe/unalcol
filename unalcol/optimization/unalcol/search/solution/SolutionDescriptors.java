package unalcol.search.solution;

import unalcol.descriptors.Descriptors;
import unalcol.search.Goal;
import unalcol.object.Tagged;

public class SolutionDescriptors<T> implements Descriptors{
	protected Goal<T,Double> goal;

	public SolutionDescriptors( Goal<T,Double> goal ) { this.goal = goal; }

	public double[] descriptors( Tagged<T> sol ){ return new double[]{goal.apply(sol)}; }

	@SuppressWarnings("unchecked")
	@Override
	public double[] descriptors(Object sol ){ return descriptors((Tagged<T>)sol); }
	
	@Override
	public String toString(){ return "SolutionDescriptor"; }	
}