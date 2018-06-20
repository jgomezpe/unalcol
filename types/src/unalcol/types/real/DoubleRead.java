package unalcol.types.real;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;

public interface DoubleRead extends Read{
	public Double read(UnalcolIterator<?, Integer> reader) throws IOException;

	@Override
	default Object read(Object obj, UnalcolIterator<?, Integer> reader) throws IOException{ return read(reader); }
}