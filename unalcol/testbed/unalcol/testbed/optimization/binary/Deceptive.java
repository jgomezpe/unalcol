package unalcol.testbed.optimization.binary;

import unalcol.bit.Array;
import unalcol.optimization.*;

/**
 * <p>Title: Deceptive</p>
 * <p>Description: The OptimizationFunction of a binary array is the 3-order deceptive function
 * as proposed by Goldberg</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class Deceptive extends OptimizationFunction<Array>{
  /**
   * Return the integer value codified by the bits in a section of the array
   * @param genes Bitarray source
   * @param start Index of the first bit in the section to extract the index
   * @param length Size of the section from which the integer is extracted
   * @return The integer value codified by the bits in a section of the array
   */
  public int getValue(Array genes, int start, int length) {
    int s = 0;
    int b = 1;
    length += start;
    try{
    	for( int i=start; i<length; i++ ){
    		if( genes.get(i) ){ s += b; };
    		b *= 2;
    	}
    }catch(Exception e){}	
    return s;
  }

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double compute( Array x ){
    int gene_size = 3;
    double f=0.0;
    int n = x.size() / gene_size;
    for( int i=0; i<n; i++ ){
      int k = getValue(x, gene_size*i, gene_size);
      switch( k ){
        case 0: f += 28; break;
        case 1: f += 26; break;
        case 2: f += 22; break;
        case 3: f += 0; break;
        case 4: f += 14; break;
        case 5: f += 0; break;
        case 6: f += 0; break;
        case 7: f += 30; break;
      }
    }
    return f;
  }
}
