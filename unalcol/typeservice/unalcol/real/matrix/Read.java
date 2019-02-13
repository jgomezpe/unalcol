package unalcol.real.matrix;

import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

public interface Read extends unalcol.io.Read{
	public double[][] read( Backable<Integer> reader ) throws ParamsException;
	default Object read( Object obj, Backable<Integer> reader ) throws ParamsException{ return read(reader); }
}