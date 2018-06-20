package unalcol.types.real.array;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;

public interface DoubleArrayRead extends Read{
	public double[] read( UnalcolIterator<?,Integer> reader ) throws IOException;
	default Object read( Object obj, UnalcolIterator<?,Integer> reader ) throws IOException{ return read(reader); }
}