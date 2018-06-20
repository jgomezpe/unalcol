package unalcol.evolution.haea;

import unalcol.descriptors.Describable;
import unalcol.descriptors.Descriptors;

public class HaeaStepDescriptors<T> implements Descriptors {
	public double[] descriptors( HaeaStep<T> step ){ return (double[])Describable.cast(step.operators()).descriptors(); }

	@SuppressWarnings("unchecked")
	@Override
	public double[] descriptors(Object obj) { return descriptors((HaeaStep<T>)obj); }
}