/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.real.array;

import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

/**
 *
 * @author jgomez
 */
public class PlainRead implements Read{
	protected boolean read_dimension = true;
	protected char separator = ' ';
	protected int n=-1;
	protected unalcol.integer.Read ri = new unalcol.integer.PlainRead();
	protected unalcol.real.Read rr = new unalcol.real.PlainRead();
	
	public PlainRead(){}
	
	public PlainRead( char separator ){
		this.separator = separator;
	}
	
	public PlainRead( int n ){
		this.n = n;
		read_dimension = (n <=0 );
	}
	
	public PlainRead( int n, char separator ){
		this.n = n;
		this.separator = separator;
		read_dimension = (n <=0 );
	}

	public void setReadInt( unalcol.integer.Read ri ){ this.ri = ri; }
	public void setReadDouble( unalcol.real.Read rr ){ this.rr = rr; }
	
    @Override
    public double[] read( Backable<Integer> reader ) throws ParamsException{
        if( read_dimension ){
        	n = ri.read(reader);
        	unalcol.io.Read.readSeparator(reader, separator);        	
        }
		double[] a = new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = rr.read(reader);
            unalcol.io.Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = rr.read(reader);
        return a;
    }
    
	@Override
	public String toString(){ return "DoubleArrayPlainRead"; }    
}