package unalcol.optimization.binary.testbed;

import unalcol.optimization.*;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title: BoundedlyDeceptive</p>
 * <p>Description: The OptimizationFunction of a binary array is the 4-order bounded deceptive function
 * as proposed by Goldberg</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class BoundedlyDeceptive extends OptimizationFunction<BitArray> {
  /**
   * Return the integer value codified by the bits in a section of the array
   * @param genes Bitarray source
   * @param start Index of the first bit in the section to extract the index
   * @param length Size of the section from which the integer is extracted
   * @return The integer value codified by the bits in a section of the array
   */
  public int getValue(BitArray genes, int start, int length) {
    int u = 0;
    int end = length + start;
    for (int i = start; i < end; i++) {
     if (genes.get(i)) { u++; };
    }
    if (u < length) { u = 3 - u; }
    return u;
  }

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double apply(BitArray x) {
    int geneSize = 4;
    double f = 0.0;
    int n = x.size() / geneSize;
    for (int i = 0; i < n; i++) {
      int k = getValue(x, geneSize * i, geneSize);
      f += k;
    }
    return f;
  }
}
