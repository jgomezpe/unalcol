package unalcol.optimization.binary;

import unalcol.types.collection.bitarray.BitArray;
import unalcol.clone.*;
import unalcol.random.raw.RawGenerator;
import unalcol.search.variation.Variation_1_1;

/**
 * <p>Title: SingleBitMutation</p>
 * <p>Description: Flips one bit in the chromosome. The flipped bit is randomly selected
 * with uniform probability distribution</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SingleBitMutation extends Variation_1_1<BitArray> {

  /**
   * Flips a bit in the given genome
   * @param genome Genome to be modified
   * @return Index of the flipped bit
   */
  public BitArray apply(BitArray genome) {
      try {
          genome = (BitArray) Clone.create(genome);
          int pos = -1;
          try {
              RawGenerator g = RawGenerator.get(this);
              pos = g.integer(genome.size());
              genome.not(pos);
          } catch (Exception e) {
              System.err.println("[Mutation]" + e.getMessage());
          }          
          return genome;
      } catch (Exception e) {}
      return null;
  }
}