/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.io.IOException;
import java.io.StringReader;
import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.integer.IntegerReadService;
import unalcol.types.real.DoubleReadService;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorSimpleReadService 
    extends Read<SparseRealVector>{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    public static final int READ_DIMENSION = -1;
    public static final int USE_ARGUMENT_DIMENSION = -2;    
    protected int dim = READ_DIMENSION;

    protected IntegerReadService integer = new IntegerReadService();
    protected DoubleReadService real = new DoubleReadService();

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public SparseRealVectorSimpleReadService() {}

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public SparseRealVectorSimpleReadService(char separator) {
        this.separator = separator;
    }

    public SparseRealVectorSimpleReadService(int dim) {
        this.dim = dim;
    }

    public SparseRealVectorSimpleReadService(char separator, int dim) {
        this.dim = dim;
        this.separator = separator;
    }

    /**
     * Returns the Class that owns the PlugIn
     * @return Class The PlugIns owner class
     */
    public Object owner() {
        return SparseRealVector.class;
    }
    
    public SparseRealVector read( SparseRealVector x, ShortTermMemoryReader reader )
            throws IOException{
        int n = dim;
        if( n == USE_ARGUMENT_DIMENSION ){
            n = x.dim();
        }
        return read(reader, n);
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
    
    public SparseRealVector read( ShortTermMemoryReader reader, int n ) throws IOException{
        if( n == READ_DIMENSION ){
            n = integer.read(reader);
            readSeparator(reader, separator);
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

    public static void main(String[] args ){
        StringReader r = new StringReader("  3, 5.0, 1, -1234.4555e-123, 6, 345.6789, 9, 23.456    ");
        ShortTermMemoryReader reader = new ShortTermMemoryReader(r);
        SparseRealVector x;
        SparseRealVectorSimpleReadService service = new SparseRealVectorSimpleReadService(',');
        try{
           x = (SparseRealVector)service.read(reader, 10);
           for( int i=0; i<x.dim(); i++ ){
               System.out.println(x.get(i));
           }
        }catch(Exception e ){
            e.printStackTrace();
        }
    }

}

