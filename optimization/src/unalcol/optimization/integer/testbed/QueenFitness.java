/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.integer.testbed;

import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;

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
    return f;
  }
  
  public static void main( String[] args ){
      int[] x = IntArray.random(8, 8);
      IntArrayPlainWrite write = new IntArrayPlainWrite(',', false);
      Write.set(int[].class, write);
      System.out.print(Write.toString(x));
      QueenFitness f = new QueenFitness();
      System.out.println(":"+f.apply(x));
  }

}

