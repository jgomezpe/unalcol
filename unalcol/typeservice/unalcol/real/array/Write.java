package unalcol.real.array;

import java.io.Writer;

import unalcol.exception.ParamsException;

public interface Write extends unalcol.io.Write{
	public void write(double[] obj, Writer out) throws ParamsException; 
	default void write(Object obj, Writer out) throws ParamsException{ write((double[])obj,out); } 
}