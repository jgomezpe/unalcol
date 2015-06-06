package unalcol.types.integer.array;
import java.io.IOException;

import unalcol.io.*;


/**
 * <p>Integer array persistent method</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public abstract class IntArrayPlainRead extends Read<int[]>{
	protected boolean read_dimension = true;
	protected char separator = ' ';
	protected int n=-1;
	
	public IntArrayPlainRead(){}
	
	public IntArrayPlainRead( char separator ){
		this.separator = separator;
	}
	
	public IntArrayPlainRead( int n ){
		this.n = n;
		read_dimension = (n <=0 );
	}
	
	public IntArrayPlainRead( int n, char separator ){
		this.n = n;
		this.separator = separator;
		read_dimension = (n <=0 );
	}
	
    /**
     * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
     * @param reader The reader object
     * @throws IOException IOException
     */
    public int[] read(ShortTermMemoryReader reader) throws IOException{
        @SuppressWarnings("unchecked")
		Read<Integer> ri = (Read<Integer>)get(Integer.class); 
        if( read_dimension ){
        	n = ri.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        int[] a = new int[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = ri.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = ri.read(reader);
        return a;
    }
}