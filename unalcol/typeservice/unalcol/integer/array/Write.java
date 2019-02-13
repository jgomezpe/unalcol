package unalcol.integer.array;

import java.io.Writer;

import unalcol.exception.ParamsException;

public interface Write extends unalcol.io.Write{
    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws ParamsException ParamsException
     */
    public void write(int[] obj, Writer out) throws ParamsException;
    
    default void write(Object obj, Writer out ) throws ParamsException{ write( (int[])obj, out); }
}