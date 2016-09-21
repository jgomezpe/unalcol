package unalcol.optimization.binary.varlength;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.clone.*;
import unalcol.search.variation.Variation_2_2;

/**
 * <p>Title: Join</p>
 * <p>Description: Joins two chromosomes</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Join extends Variation_2_2<BitArray>{

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public BitArray[] apply(BitArray c1, BitArray c2) {
      try {
          BitArray genome1 = (BitArray) Clone.create(c1);
          genome1.add(c2);
          BitArray genome2 = (BitArray) Clone.create(c2);
          genome2.add(c1);
          return new BitArray[]{genome1, genome2};
      } catch (Exception e) {
      }
      return null;
  }
}