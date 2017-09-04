/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import java.util.Iterator;

import unalcol.clone.Clone;
import unalcol.clone.DefaultClone;
import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.types.collection.array.ArrayLocation;
import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

/**
 *
 * @author Jonatan
 */
public class VectorTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);         
    	service.register(new DoubleArrayPlainRead(), double[].class);
        service.register(new DoubleArrayPlainWrite(), double[].class);
        service.register(new DefaultClone(), Object.class);
        service.register(new VectorClone<Object>(), Vector.class);
//        service.register(new ConsoleTracer(), Object.class);
        Service.set(service);
	}

	public static void main( String[] args ){
		init_services();
		
      Integer[] x = new Integer[50];
      for( int i=0; i<x.length; i++){
          x[i] = i;
      }

      Vector<Integer> v = new Vector<Integer>(x);
      for( int i=0; i<10; i++ ){
          v.remove(i);
      }
      

      System.out.println("Using a regular for loop");
      for(int i=0; i<v.size(); i++ ){
          System.out.println( v.get(i) );
      }
      
      System.out.println("Using an iteration approach");
      for( Integer k:v){
          System.out.println( k );          
      }

      System.out.println("Using a locator approach");
      ArrayLocation<Integer> loc = new ArrayLocation<Integer>(4,v);
      for(Iterator<Integer> iter = loc.iterator(); iter.hasNext(); ){
          System.out.println( iter.next() );          
      }

            
      try{
	      @SuppressWarnings("unchecked")
	  	  Vector<Integer> clone = (Vector<Integer>)Service.run(Clone.name, v);
	      if( clone != v ){
		      System.out.println("Clone");
		      for( Integer k:clone){
		          System.out.println( k );          
		      }
	      }
      }catch(Exception e){e.printStackTrace();}
    }  


//        System.out.println( 5 >> 2 );
//        System.out.println( (5 & 4) == 4 );
//        System.out.println( 10 << 2 );
        
//      ArrayList<Integer> v = new ArrayList<>();
/*      long m, p, n;

      n  = System.nanoTime();
      for( int i=0; i<100000; i++ ){
          v.add( i );
      }
      p = System.nanoTime();
      m = p - n;
      System.out.print( m + " " );
      
      n = System.nanoTime();
      for(int i=0; i<100000; i++ ){
          v.add(i,-i);
      }
      p = System.nanoTime();
      m = p - n;
      System.out.print( m  + " " );

      n = System.nanoTime();
      for(int i=0; i<100000; i++ ){
          v.remove(i);
//          v.remove(i);
      }
      p = System.nanoTime();
      m = p - n;
      System.out.print( m );

      n = System.nanoTime();
      for(int i=0; i<99000; i++ ){
          v.remove(0);
//          v.remove(i);
      }
      p = System.nanoTime();
      m = p - n;
      System.out.println( m );

      for(int i=0; i<1000; i++ ){
          System.out.println( v.get(i));
      }
*/      
    
}
