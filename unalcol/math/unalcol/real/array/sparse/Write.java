package unalcol.real.array.sparse;

import java.io.Writer;

import unalcol.exception.ParamsException;

public interface Write extends unalcol.io.Write{
	public void write(Vector obj, Writer out) throws ParamsException; 
	default void write(Object obj, Writer out) throws ParamsException{ write((Vector)obj,out); } 
}