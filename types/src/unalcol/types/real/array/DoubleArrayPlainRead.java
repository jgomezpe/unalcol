/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real.array;
import java.io.IOException;

import unalcol.io.*;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.integer.IntegerRead;
import unalcol.types.real.DoublePlainRead;
import unalcol.types.real.DoubleRead;

/**
 *
 * @author jgomez
 */
public class DoubleArrayPlainRead implements DoubleArrayRead{
	protected boolean read_dimension = true;
	protected char separator = ' ';
	protected int n=-1;
	protected IntegerRead ri = new IntegerPlainRead();
	protected DoubleRead rr = new DoublePlainRead();
	
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

	public void setReadInt( IntegerRead ri ){ this.ri = ri; }
	public void setReadDouble( DoubleRead rr ){ this.rr = rr; }
	
    @Override
    public double[] read( UnalcolIterator<?,Integer> reader ) throws IOException{
        if( read_dimension ){
        	n = ri.read(reader);
            Read.readSeparator(reader, separator);        	
        }
		double[] a = new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = rr.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = rr.read(reader);
        return a;
    }
    
	@Override
	public String toString(){ return "DoubleArrayPlainRead"; }    
}