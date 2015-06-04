/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.util;

import unalcol.optimization.OptimizationFunction;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.integer.array.IntArraySimplePersistent;

/**
 *
 * @author Jonatan
 */
public class QueenFitness extends OptimizationFunction<int[]>{
  public QueenFitness(){}
  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  @Override  
  public Double apply( int[] x ){
    double f = 0.0;
    for( int i=0; i<x.length; i++ ){
        for( int j=0; j<x.length; j++){
            if(i!=j){
                f += (x[i]==x[j] || x[i]==x[j]+i-j || x[i]==x[j]+j-i)?1:0;
            }
        }
    }
    return -f;
  }
  
  public static void main( String[] args ){
      int[] x = new int[8];
      IntArrayInstance ins = new IntArrayInstance(8);
      x = ins.get(x);
      //IntArraySimplePersistent per = new IntArraySimplePersistent(',');
      System.out.println(XOverIntArray.toStringInt(x));
      QueenFitness f = new QueenFitness();
      System.out.println(":"+f.apply(x));
  }

}

