package unalcol.optimization.binary.testbed;

import unalcol.optimization.*;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title: M7_Deceptive</p>
 * <p>Description: Extended deceptive binary functions</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class M7_Deceptive extends OptimizationFunction<BitArray>{

  /**
   * Return the integer value codified by the bits in a section of the array
   * @param genes Bitarray source
   * @param start Index of the first bit in the section to extract the index
   * @param length Size of the section from which the integer is extracted
   * @return The integer value codified by the bits in a section of the array
   */
  public int getValue(BitArray genes, int start, int length) {
    int s = 0;
    length += start;
    for( int i=start; i<length; i++ ){
     if( genes.get(i) ){ s++; };
    };
    return s;
  }

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double apply( BitArray x ){
    int gene_size = 6;
    double f=0.0;
    int n = x.size() / gene_size;
    for( int i=0; i<n; i++ ){
      int k = getValue(x, gene_size*i, gene_size);
      switch( k ){
        case 0:
        case 6:
          f += 1;
        break;
//        case 1: f += 0; break;
        case 2:
        case 4:
          f += 0.4;
        break;
        case 3: f += 0.640576; break;
//        case 5: f += 0; break;
      }
    }
    return f;
  }
}
