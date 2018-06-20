/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.integer.IntegerRead;
import unalcol.types.real.DoublePlainRead;
import unalcol.types.real.DoubleRead;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorPlainRead implements SparseRealVectorRead{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    protected boolean read_dimension = true;
    protected int n = -1;
	protected IntegerRead ri = new IntegerPlainRead();
	protected DoubleRead rr = new DoublePlainRead();

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public SparseRealVectorPlainRead() {}

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public SparseRealVectorPlainRead(char separator) {
        this.separator = separator;
    }

    public SparseRealVectorPlainRead(int n) {
        this.n = n;
        read_dimension = n<=0;
    }

    public SparseRealVectorPlainRead(int n, char separator) {
        this.n = n;
        read_dimension = n<=0;
        this.separator = separator;
    }

	public void setReadInt( IntegerRead ri ){ this.ri = ri; }
	public void setReadDouble( DoubleRead rr ){ this.rr = rr; }
	
	public SparseRealVector read( UnalcolIterator<?, Integer> reader ) throws IOException{
        if( read_dimension ){
        	n = ri.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        SparseRealVector d = new SparseRealVector(n);
        int k;
        double v;
        while( reader.hasNext() ){
            k = ri.read(reader);
            Read.readSeparator(reader, separator);
            v = rr.read(reader);
            Read.readSeparator(reader, separator);
            d.set(k,(Double)v);
        }
        return d;
    }
}