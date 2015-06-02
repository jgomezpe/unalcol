package unalcol.optimization.binary.testbed;

import unalcol.optimization.*;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title: RoyalRoad</p>
 * <p>Description: The OptimizationFunction of a binary array is the royal road function
 * as proposed by Mickalewicks</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class RoyalRoad extends OptimizationFunction<BitArray>{
  /**
   * The royal road path length
   */
  protected int pathLength = 8;

  /**
   * Constructor: Create a royal road OptimizationFunction function with the path given
   * @param _pathLength The royal road path length
   */
  public RoyalRoad( int _pathLength ){
    pathLength = _pathLength;
  }

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double apply( BitArray x ){
    double f=0.0;
    int n = x.size() / pathLength;
    for( int i=0; i<n; i++ ){
      int start = pathLength*i;
      int end = start+pathLength;
      while( start < end && x.get(start) ){ start++; }
      if( start == end ){ f += pathLength; }
    }
    return f;
  }
}
