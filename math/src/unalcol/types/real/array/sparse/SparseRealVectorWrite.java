package unalcol.types.real.array.sparse;

import java.io.IOException;
import java.io.Writer;

public interface SparseRealVectorWrite {
	public void write(SparseRealVector obj, Writer out) throws IOException; 
	default void write(Object obj, Writer out) throws IOException{ write((SparseRealVector)obj,out); } 
}