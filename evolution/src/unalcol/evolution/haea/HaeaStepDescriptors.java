package unalcol.evolution.haea;

import unalcol.descriptors.Descriptors;
import unalcol.services.MicroService;

public class HaeaStepDescriptors<T> extends MicroService<HaeaStep<T>> implements Descriptors<HaeaStep<T>> {
	@Override
	public double[] descriptors() {
		return (double[])Descriptors.create(caller().operators());
	}
}