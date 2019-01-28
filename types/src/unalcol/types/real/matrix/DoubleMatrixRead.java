package unalcol.types.real.matrix;

import java.io.IOException;

import unalcol.types.collection.iterator.UnalcolIterator;

public interface DoubleMatrixRead {
	public double[][] read( UnalcolIterator<Integer> reader ) throws IOException;
	default Object read( Object obj, UnalcolIterator<Integer> reader ) throws IOException{ return read(reader); }
}