package unalcol.types.real.array;

import java.io.IOException;
import java.io.Writer;

import unalcol.io.Write;

public interface DoubleArrayWrite extends Write{
	public void write(double[] obj, Writer out) throws IOException; 
	default void write(Object obj, Writer out) throws IOException{ write((double[])obj,out); } 
}