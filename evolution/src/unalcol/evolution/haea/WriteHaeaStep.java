package unalcol.evolution.haea;

import java.io.IOException;
import java.io.Writer;

import unalcol.io.Writable;
import unalcol.io.Write;

public class WriteHaeaStep<T> implements Write{
	public void write(HaeaStep<T> step, Writer writer) throws IOException{ Writable.cast(step.operators()).write(writer); }

	@SuppressWarnings("unchecked")
	@Override
	public void write(Object obj, Writer writer) throws IOException { write((HaeaStep<T>)obj, writer); }
}