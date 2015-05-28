package unalcol.types.integer.array;

import unalcol.io.*;
import java.io.*;

/**
 * <p>Integer array persistent method that uses a given charater for separating the array values</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class IntArraySimplePersistent extends IntArrayPersistent {
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public IntArraySimplePersistent() {}

    /**
     * Creates an integer array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public IntArraySimplePersistent(char separator) {
        this.separator = separator;
    }

    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws IOException IOException
     */
    public void write(int[] obj, Writer out) throws IOException {
        int n = obj.length;
        out.write(n);
        for (int i = 0; i < n; i++) {
            out.write(separator);
            out.write(n);
        }
    }

    /**
     * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
     * @param reader The reader object
     * @throws IOException IOException
     */
    public Object read(ShortTermMemoryReader reader) throws Exception {
        StreamTokenizer tok = new StreamTokenizer(reader);
        tok.nextToken();
        int n = (int) tok.nval;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            tok.nextToken();
            a[i] = (int) tok.nval;
        }
        return a;
    }
}
