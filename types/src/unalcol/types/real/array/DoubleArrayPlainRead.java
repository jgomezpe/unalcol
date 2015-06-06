/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real.array;
import unalcol.io.*;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.real.DoublePlainRead;

import java.io.*;

/**
 *
 * @author jgomez
 */
public class DoubleArrayPlainRead extends Read<double[]>{
	protected boolean read_dimension = true;
	protected char separator = ' ';
	protected int n=-1;
	
	public DoubleArrayPlainRead(){}
	
	public DoubleArrayPlainRead( char separator ){
		this.separator = separator;
	}
	
	public DoubleArrayPlainRead( int n ){
		this.n = n;
		read_dimension = (n <=0 );
	}
	
	public DoubleArrayPlainRead( int n, char separator ){
		this.n = n;
		this.separator = separator;
		read_dimension = (n <=0 );
	}
	
    @Override
    public double[] read( ShortTermMemoryReader reader ) throws IOException{
        @SuppressWarnings("unchecked")
		Read<Double> rd = (Read<Double>)get(Double.class); 
        if( rd==null ){
        	rd = new DoublePlainRead();
        	set( Double.class, rd);        	
        }
        if( read_dimension ){
            @SuppressWarnings("unchecked")
    		Read<Integer> ri = (Read<Integer>)get(Integer.class); 
            if( ri == null ){
            	ri = new IntegerPlainRead();
            	set( Integer.class, ri);
            }
        	n = ri.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        double[] a = new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = rd.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = rd.read(reader);
        return a;
    }
}
