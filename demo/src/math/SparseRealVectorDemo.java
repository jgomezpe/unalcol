/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.io.StringReader;

import unalcol.io.CharReader;
import unalcol.services.Service;
import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorDotProduct;
import unalcol.types.real.array.sparse.SparseRealVectorPlainRead;
import unalcol.types.real.array.sparse.SparseRealVectorSpace;
import unalcol.types.real.array.sparse.SparseRealVectorSphereNormalization;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorDemo {
	public static void init_services(){
    	Service.register(new DoubleArrayPlainRead(), double[].class);
        Service.register(new DoubleArrayPlainWrite(), double[].class);
        Service.register(new VectorClone<Object>(), Vector.class);
//        service.register(new ConsoleTracer(), Object.class);
        Service.register(new DoubleArrayPlainWrite(','),double[].class);
	}
	
	public static void persistency(){
		init_services();
        StringReader r = new StringReader("  3, 5.0, 1, -1234.4555e-123, 6, 345.6789, 9, 23.456    ");
        CharReader reader = new CharReader(r);
        SparseRealVector x;
        SparseRealVectorPlainRead service = new SparseRealVectorPlainRead(10, ',');
        try{
           x = (SparseRealVector)service.read(reader.unalcol());
           for( int i=0; i<x.size(); i++ ){
               System.out.println(x.get(i));
           }
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
    

	
	public static void main( String[] args ){
         // Reflection
       
        SparseRealVector x = new SparseRealVector(100);
        for( int i=0; i<10; i++){
            x.set(i*10, (Double)((double)(i+1)));
        }
        
        System.out.println( x.size() );
        SparseRealVector y = new SparseRealVector(100);
        for( int i=0; i<10; i++){
            y.set(i*5, (Double)((double)(i+1)));
        }
        System.out.println( y.size() );
        for( int i=0; i<100; i++ ){
            System.out.println(x.get(i));
        }
        
        System.out.println("######################");
        SparseRealVectorSpace add = new SparseRealVectorSpace();
        try{
            SparseRealVector z = add.plus(x, y);
            for( int i=0; i<100; i++ ){
                System.out.println(z.get(i));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        SparseRealVectorDotProduct product = new SparseRealVectorDotProduct();
        System.out.println("Product"+product.apply(x, y));
        
        SparseRealVectorSphereNormalization norm =new SparseRealVectorSphereNormalization();
        norm.fastApply(x);
        for( int i=0; i<100; i++ ){
            System.out.println(x.get(i));
        }
    }
    
}
