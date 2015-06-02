package unalcol.optimization.binary.testbed;

import unalcol.optimization.*;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title: MaxOnes</p>
 * <p>Description: The OptimizationFunction of a binary array is the number of ones in the array</p>
 * <p>Copyright:    Copyright (c) 2010/p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class MaxOnes extends OptimizationFunction<BitArray>{

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double apply( BitArray x ){
    double f = 0.0;
    for( int i=0; i<x.size(); i++ ){
      if( x.get(i) ){ f++; }
    }
    return f;
  }

}
