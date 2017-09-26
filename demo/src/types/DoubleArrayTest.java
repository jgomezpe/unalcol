/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import unalcol.io.CharReader;
import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.io.Write;
import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.rngpack.RanMT;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.real.DoubleOrder;
import unalcol.types.real.DoublePlainRead;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

/**
 *
 * @author Jonatan
 */
public class DoubleArrayTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);         
    	service.register(new DoubleOrder(), double[].class);
    	service.register(new DoublePlainRead(), Double.class);
    	service.register(new IntegerPlainRead(), Integer.class);
    	service.register(new DoubleArrayPlainRead(), double[].class);
        service.register(new DoubleArrayPlainWrite(), double[].class);
//        service.register(new ConsoleTracer(), Object.class);
        Service.set(service);
	}

	public static double[] sort(){
        RawGenerator g = new RanMT();
        int N = 1000;
        double[] x = g.raw(N);
        return sort(x);
    }
    
    public static double[] sort( double[] x ){
    	System.out.println("Sorted array...");
        DoubleArray.merge(x);
        System.out.println(Write.toString(x));
        return x;
    }    
    
    public static double[] persistency(){
        // The first value is the number of real values, followed by the values
        // to be stored in the double array
        ShortTermMemoryReader reader = new CharReader("  3  -1234.4555e-123 345.6789 23.456");
        double[] x = new double[0];
        try{
           // Reading the array from the provided buffer (reader) 
           x = (double[])Service.run(Read.name,double[].class, reader);
           // Printing the array using a regular for loop
           for( int i=0; i<x.length; i++ ){
               System.out.println(x[i]);
           }
           // Using a service for printing the array. Here we register the plain text
           // writing service for double arrays and use it by default           
           // Printing to a String
           System.out.println(Write.toString(x));
           return x;
        }catch(Exception e ){
            e.printStackTrace();
        }           
        return null;
    }
    
    public static void main( String[] args ){
    	init_services();
        double[] x = persistency();
        x = sort(x);
    }
}
