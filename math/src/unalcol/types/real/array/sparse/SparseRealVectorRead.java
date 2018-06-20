package unalcol.types.real.array.sparse;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;

public interface SparseRealVectorRead extends Read{
	public SparseRealVector read(UnalcolIterator<?, Integer> reader) throws IOException;

	@Override
	default Object read(Object obj, UnalcolIterator<?, Integer> reader) throws IOException{ return read(reader); }
}