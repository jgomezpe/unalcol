package unalcol.optimization.binary;

import unalcol.types.collection.bitarray.BitArray;
import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.RawGeneratorWrapper;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.AbstractMicroService;
import unalcol.services.MicroService;

/**
 * <p>Title: SingleBitMutation</p>
 * <p>Description: Flips one bit in the chromosome. The flipped bit is randomly selected
 * with uniform probability distribution</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SingleBitMutation extends MicroService<BitArray> implements Variation_1_1<BitArray> {
	public AbstractMicroService<?> wrap(String id){
		if(id.equals(RawGenerator.name)) return new RawGeneratorWrapper();
		return null;
	}
  /**
   * Flips a bit in the given genome
   * @param genome Genome to be modified
   * @return Index of the flipped bit
   */
  public BitArray apply(BitArray genome) {
      try {
          genome = new BitArray(genome);
          int pos = -1;
          try {
              RawGenerator g = (RawGenerator)getMicroService(RawGenerator.name);
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