package unalcol.evolution.haea;

import java.io.Writer;

import unalcol.exception.ParamsException;
import unalcol.io.Writable;
import unalcol.io.Write;

public class WriteHaeaStep<T> implements Write{
	public void write(HaeaStep<T> step, Writer writer) throws ParamsException{ Writable.cast(step.operators()).write(writer); }

	@SuppressWarnings("unchecked")
	@Override
	public void write(Object obj, Writer writer) throws ParamsException { write((HaeaStep<T>)obj, writer); }
}