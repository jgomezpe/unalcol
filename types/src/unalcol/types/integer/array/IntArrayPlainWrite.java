package unalcol.types.integer.array;

import unalcol.io.*;

import java.io.*;

/**
 * <p>Integer array persistent method that uses a given character for separating the array values</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class IntArrayPlainWrite extends Write<int[]>{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    protected boolean write_dimension = true;

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public IntArrayPlainWrite() {}

    /**
     * Creates an integer array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public IntArrayPlainWrite(char separator) {
        this.separator = separator;
    }
    
    public IntArrayPlainWrite(char separator, boolean write_dimension) {
        this.separator = separator;
        this.write_dimension = write_dimension;
    }

    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws IOException IOException
     */
    public void write(int[] obj, Writer out) throws Exception {
        @SuppressWarnings("unchecked")
		Write<Integer> wi = (Write<Integer>)get(Integer.class); 
        int n = obj.length;
        if( write_dimension ) wi.write(n, out);
        for (int i = 0; i < n; i++) {
            out.write(separator);
            wi.write(obj[i], out);
        }
    }
}