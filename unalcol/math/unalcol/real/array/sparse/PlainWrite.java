/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import java.io.IOException;
import java.io.Writer;

import unalcol.exception.ParamsException;
import unalcol.collection.Collection;
import unalcol.collection.keymap.KeyValue;
import unalcol.constants.InnerCore;

/**
 *
 * @author jgomez
 */
public class PlainWrite implements Write{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    protected boolean write_dimension = true;

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public PlainWrite() {}

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public PlainWrite(boolean write_dim ) {
        write_dimension = write_dim;
    }

    /**
     * Creates a double array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainWrite(char separator) {
        this.separator = separator;
    }

    /**
     * Creates a double array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainWrite(char separator, boolean write_dim ) {
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
    public void write(Vector obj, Writer out) throws ParamsException {
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
        try{ out.write(sb.toString()); }catch(IOException e){ throw ParamsException.wrap(InnerCore.IO, e); }
    }
}
