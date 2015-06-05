/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.io.StringReader;

import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.io.Write;
import unalcol.random.raw.RawGenerator;
import unalcol.random.rngpack.RanMT;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;
import static unalcol.types.real.array.DoubleArraySort.merge;

/**
 *
 * @author Jonatan
 */
public class DoubleArrayTest {
    public static double[] sort(){
        RawGenerator g = new RanMT();
        int N = 1000;
        double[] x = g.raw(N);
        return sort(x);
    }
    
    public static double[] sort( double[] x ){
        int N = x.length;
        merge(x);
        for( int i=0; i<N; i++ ){
            System.out.println( x[i] + "," + (N-i) );
        }
        return x;
    }    
    
    public static double[] persistency(){
        // Registering the PlainRead service (reading double arrays as plain text,
        // notice that an instance of the Plain read service is provided.
        Read.set(double[].class, new DoubleArrayPlainRead());
        // The first value is the number of real values, followed by the values
        // to be stored in the double array
        StringReader r = new StringReader("  3  -1234.4555e-123 345.6789 23.456");
        ShortTermMemoryReader reader = new ShortTermMemoryReader(r);
        double[] x = new double[0];
        try{
           // Reading the array from the provided buffer (reader) 
           x = (double[])Read.apply(x, reader);
           // Printing the array using a regular for loop
           for( int i=0; i<x.length; i++ ){
               System.out.println(x[i]);
           }
           // Using a service for printing the array. Here we register the plain text
           // writing service for double arrays and use it by default           
           Write.set(double[].class, new DoubleArrayPlainWrite());
           // Printing to a String
           System.out.println(Write.toString(x));
           return x;
        }catch(Exception e ){
            e.printStackTrace();
        }           
        return null;
    }
    
    public static void main( String[] args ){
        double[] x = persistency();
        x = sort(x);
    }
}
