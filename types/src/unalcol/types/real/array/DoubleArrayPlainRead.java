/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real.array;
import java.io.IOException;

import unalcol.io.*;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.integer.UsesIntRead;
import unalcol.types.real.UsesDoubleRead;
import unalcol.types.tag.Tags;

/**
 *
 * @author jgomez
 */
public class DoubleArrayPlainRead extends Tags implements TaggedCallerNamePair<double[]>, Read<double[]>, UsesDoubleRead, UsesIntRead{
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