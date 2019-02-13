package unalcol.real;

import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

public interface Read extends unalcol.io.Read{
	public Double read(Backable<Integer> reader) throws ParamsException;

	@Override
	default Object read(Object obj, Backable<Integer> reader) throws ParamsException{ return read(reader); }
}