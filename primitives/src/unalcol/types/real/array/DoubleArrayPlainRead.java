/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real.array;
import java.io.IOException;

import unalcol.io.*;
import unalcol.services.AbstractMicroService;
import unalcol.services.MicroService;

/**
 *
 * @author jgomez
 */
public class DoubleArrayPlainRead extends MicroService<double[]> implements Read<double[]>{
	public static final String integer = "Read.integer";
	public static final String real = "Read.double";
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

	public AbstractMicroService<?> wrap(String id){
		if( id.equals(integer) ) return new ReadWrapper<Integer>();
		if( id.equals(real) ) return new ReadWrapper<Double>();
		return null;
	}
	
    @Override
    public double[] read( ShortTermMemoryReader reader ) throws IOException{
        if( read_dimension ){
        	@SuppressWarnings("unchecked")
    		Read<Integer> ri = (Read<Integer>)getMicroService(integer);
        	ri.setCaller(n);
        	n = ri.read(reader);
            Read.readSeparator(reader, separator);        	
        }
    	@SuppressWarnings("unchecked")
		Read<Double> rr = (Read<Double>)getMicroService(real);
    	rr.setCaller(0.0);
		double[] a = new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = rr.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = rr.read(reader);
        return a;
    }
}