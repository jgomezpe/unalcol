package unalcol.optimization.binary.varlength;
import unalcol.bit.Array;
import unalcol.search.variation.Variation_2_2;

/**
 * <p>Title: Join</p>
 * <p>Description: Joins two chromosomes</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Join implements Variation_2_2<Array>{
  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public Array[] apply(Array c1, Array c2) {
      try {
          Array genome1 = new Array(c1);
          genome1.add(c2);
          Array genome2 = new Array(c2);
          genome2.add(c1);
          return new Array[]{genome1, genome2};
      } catch (Exception e) {
      }
      return null;
  }
}