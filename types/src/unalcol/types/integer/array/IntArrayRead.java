package unalcol.types.integer.array;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;

public interface IntArrayRead extends Read{

	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws IOException IOException
	 */
    public int[] read(UnalcolIterator<?,Integer> reader) throws IOException;

    @Override
    default Object read(Object obj, UnalcolIterator<?, Integer> reader) throws IOException{ return read(reader); }
}