package unalcol.integer.array;

import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

public interface Read extends unalcol.io.Read{

	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws ParamsException ParamsException
	 */
    public int[] read(Backable<Integer> reader) throws ParamsException;

    @Override
    default Object read(Object obj, Backable<Integer> reader) throws ParamsException{ return read(reader); }
}