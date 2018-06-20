/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.io.IOException;
import java.io.Writer;
import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.KeyValue;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorPlainWriteService implements SparseRealVectorWrite{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    protected boolean write_dimension = true;

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public SparseRealVectorPlainWriteService() {}

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public SparseRealVectorPlainWriteService(boolean write_dim ) {
        write_dimension = write_dim;
    }

    /**
     * Creates a double array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public SparseRealVectorPlainWriteService(char separator) {
        this.separator = separator;
    }

    /**
     * Creates a double array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public SparseRealVectorPlainWriteService(char separator, boolean write_dim ) {
        this.separator = separator;
        this.write_dimension = write_dim;
    }
    
    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws IOException IOException
     */
    @Override
    public void write(SparseRealVector obj, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = obj.size();
        if( write_dimension ){
            sb.append(n);
            sb.append(separator);
        }
        Collection<KeyValue<Integer,Double>> col = obj.pairs();
        for( KeyValue<Integer,Double> kv : col ){
            sb.append(kv.key());
            sb.append(separator);
            sb.append(kv.value());            
            sb.append(separator);
        }        
        out.write(sb.toString());
    }
}
