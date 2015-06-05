/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real.array;
import unalcol.io.*;
import unalcol.types.integer.*;
import unalcol.types.real.*;
import java.io.*;

/**
 *
 * @author jgomez
 */
public class DoubleArrayPlainRead extends Read<double[]>{
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
    public DoubleArrayPlainRead() {}

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public DoubleArrayPlainRead(char separator) {
        this.separator = separator;
    }
    
    public DoubleArrayPlainRead(int dim){
        this.dim = dim;
    }

    public DoubleArrayPlainRead(int dim, char separator){
        this.dim = dim;
        this.separator = separator;
    }

    
    public double[] read( double[] x, ShortTermMemoryReader reader ) throws IOException{
        int d = dim;
        if(d==USE_ARGUMENT_DIMENSION){
            d = x.length;
        }
        return read(reader, d);
    }
    
    public double[] read( ShortTermMemoryReader reader, int n ) throws IOException{
        if( n==READ_DIMENSION){
           n = (Integer)integer.read(reader);
           Read.readSeparator(reader, separator);
        }
        double[] d = new double[n];
        for( int i=0; i<n; i++ ){
            d[i] = (Double)real.read(reader);
            Read.readSeparator(reader, separator);
        }
        return d;
    }
}
