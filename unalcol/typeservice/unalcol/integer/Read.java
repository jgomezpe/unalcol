package unalcol.integer;

import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

public interface Read extends unalcol.io.Read{
	public Integer read( Backable<Integer> reader ) throws ParamsException;
	
	@Override
	default Object read(Object one, Backable<Integer> reader) throws ParamsException { return read(reader); }	
}