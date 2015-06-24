package unalcol.evolution.haea;

import unalcol.descriptors.Descriptors;

public class HaeaStepDescriptors<T> extends Descriptors<HaeaStep<T>> {
	@Override
	public double[] descriptors(HaeaStep<T> step) {
		return Descriptors.obtain(step.operators());
	}
}