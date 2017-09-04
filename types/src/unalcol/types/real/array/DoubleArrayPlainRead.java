/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real.array;
import unalcol.io.*;
import unalcol.services.Service;

/**
 *
 * @author jgomez
 */
public class DoubleArrayPlainRead implements Read<double[]>{
	protected boolean read_dimension = true;
	protected char separator = ' ';
	protected int n=-1;
	protected Read<Integer> ri=null;
	protected Read<Double> rr=null;
	
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

	public void setIntReader( Read<Integer> ri ){ this.ri = ri; }
	
	protected int readInt(ShortTermMemoryReader reader) throws Exception{
		if( ri!=null ) return ri.read(reader);
		return (int)Service.run(Read.name, Integer.class, reader);
	}
	
	public void setDoubleReader( Read<Double> rr ){ this.rr = rr; }
	
	protected double readDouble(ShortTermMemoryReader reader) throws Exception{
		if( rr!=null ) return rr.read(reader);
		return (double)Service.run(Read.name, Double.class, reader);
	}
	
    @Override
    public double[] read( ShortTermMemoryReader reader ) throws Exception{
        if( read_dimension ){
        	n = readInt(reader);
            Read.readSeparator(reader, separator);        	
        }
		double[] a = new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = readDouble(reader);
            Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = readDouble(reader);
        return a;
    }
}