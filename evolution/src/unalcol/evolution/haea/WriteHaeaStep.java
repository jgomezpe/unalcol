package unalcol.evolution.haea;

import java.io.Writer;

import unalcol.io.Write;

public class WriteHaeaStep<T> extends Write<HaeaStep<T>> {
	@Override
	public void write(HaeaStep<T> step, Writer writer) throws Exception {
		Write.apply(step.operators(), writer);
	}
}