/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

/**
 *
 * @author jgomez
 */
public class PlainRead implements Read{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    protected boolean read_dimension = true;
    protected int n = -1;
	protected unalcol.integer.Read ri = new unalcol.integer.PlainRead();
	protected unalcol.real.Read rr = new unalcol.real.PlainRead();

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public PlainRead() {}

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainRead(char separator) {
        this.separator = separator;
    }

    public PlainRead(int n) {
        this.n = n;
        read_dimension = n<=0;
    }

    public PlainRead(int n, char separator) {
        this.n = n;
        read_dimension = n<=0;
        this.separator = separator;
    }

	public void setReadInt( unalcol.integer.Read ri ){ this.ri = ri; }
	public void setReadDouble( unalcol.real.Read rr ){ this.rr = rr; }
	
	public Vector read( Backable<Integer> reader ) throws ParamsException{
        if( read_dimension ){
        	n = ri.read(reader);
        	unalcol.io.Read.readSeparator(reader, separator);        	
        }
        Vector d = new Vector(n);
        int k;
        double v;
        while( reader.hasNext() ){
            k = ri.read(reader);
            unalcol.io.Read.readSeparator(reader, separator);
            v = rr.read(reader);
            unalcol.io.Read.readSeparator(reader, separator);
            d.set(k,(Double)v);
        }
        return d;
    }
}