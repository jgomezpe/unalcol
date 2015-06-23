package unalcol.evolution.growing;

import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.types.collection.bitarray.*;
import unalcol.types.real.array.*;
import unalcol.types.real.*;

/**
 * <p>Title: GrowingBinaryToRealVector</p>
 * <p>Description: Growing function from binary to array of double</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GrowingBinaryToRealVector extends CodeDecodeMap<BitArray, double[]>{
    protected GrowingBinaryToIntArray lowLevel = null;
    protected RealVectorLinealScale scale = null;
    protected LinealScale one_scale = null;

    public GrowingBinaryToRealVector( int _int_size, double[] min, double[] max ){
        lowLevel = new GrowingBinaryToIntArray(_int_size);
        double[] minLow = DoubleArray.create(min.length, lowLevel.min());
        double[] maxLow = DoubleArray.create(max.length, lowLevel.max());
        scale = new RealVectorLinealScale(minLow, maxLow, min, max);
    }

    /**
     * Constructor: Creates an individual with a random genome
     */
    public GrowingBinaryToRealVector(int _int_size) {
        lowLevel = new GrowingBinaryToIntArray(_int_size);
        one_scale = new LinealScale(lowLevel.min(), lowLevel.max());
    }

    public double[] decode( BitArray genome ){
        int[] y = lowLevel.decode( genome );
        int n = y.length;
        double[] x = new double[n];
        if( one_scale != null ){
            for( int i=0; i<n; i++ ){
              x[i] = one_scale.inverse(y[i]);
            }
            return x;
        }else{
            for( int i=0; i<n; i++ ){
              x[i] = y[i];
            }
            return scale.apply(x);
        }
    }
    
    public BitArray code( double[] x ){
        int n = x.length;
        if( one_scale != null){
            x = x.clone();
            for( int i=0; i<n; i++ ){
                x[i] = one_scale.apply(x[i]);
            }
        }else{    
            x = scale.inverse(x);
        }    
        int[] y = new int[n];
        for( int i=0; i<n; i++ ){
           y[i] = (int)(x[i]);
        }
        return lowLevel.code( y );
    }

    public static void main( String[] args ){
      int n = 32;
      GrowingBinaryToRealVector p = new GrowingBinaryToRealVector(n);
      for( int i=0; i<10; i++ ){
          BitArray g = new BitArray(n, true);
          System.out.println(g.toString());
          System.out.println(p.decode(g)[0]);
      }
      BitArray x = p.code(new double[]{420.9687});

      System.out.println( "***"+x );
      System.out.println( "+++"+p.decode(x)[0] );
//      System.out.println( "sin=" + Math.sin( Math.sqrt(416.9907848)) );
      System.out.println( "sin=" + 420.9687*Math.sin( Math.sqrt(420.9687)) );
      System.out.println( "sin=" + 420.953125*Math.sin( Math.sqrt(420.953125)) );

      System.out.println( "***"+p.code(new double[]{1.0}) );
   }
}
