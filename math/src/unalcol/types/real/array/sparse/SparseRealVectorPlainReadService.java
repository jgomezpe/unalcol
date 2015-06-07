/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.io.IOException;
import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.real.DoublePlainRead;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorPlainReadService 
    extends Read<SparseRealVector>{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    protected boolean read_dimension = true;
    protected int n = -1;

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public SparseRealVectorPlainReadService() {}

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public SparseRealVectorPlainReadService(char separator) {
        this.separator = separator;
    }

    public SparseRealVectorPlainReadService(int n) {
        this.n = n;
        read_dimension = n<=0;
    }

    public SparseRealVectorPlainReadService(int n, char separator) {
        this.n = n;
        read_dimension = n<=0;
        this.separator = separator;
    }

    protected boolean hasNext(ShortTermMemoryReader reader){
        try{
            if( reader.read() != -1 ){
                reader.back();
                return true;
            }
        }catch(Exception e){
        }
        return false;
    }
    
    public SparseRealVector read( ShortTermMemoryReader reader ) throws IOException{
        @SuppressWarnings("unchecked")
		Read<Double> real = (Read<Double>)get(Double.class); 
        if( real==null ){
        	real = new DoublePlainRead();
        	set( Double.class, real);        	
        }
        @SuppressWarnings("unchecked")
		Read<Integer> integer = (Read<Integer>)get(Integer.class); 
        if( integer == null ){
        	integer = new IntegerPlainRead();
        	set( Integer.class, integer);
        }
        if( read_dimension ){
        	n = integer.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        SparseRealVector d = new SparseRealVector(n);
        int k;
        double v;
        while( hasNext(reader) ){
            k = integer.read(reader);
            readSeparator(reader, separator);
            v = real.read(reader);
            readSeparator(reader, separator);
            d.set(k,v);
        }
        return d;
    }
}

