package unalcol.optimization.binary;

import unalcol.types.collection.bitarray.BitArray;
import unalcol.clone.*;
import unalcol.random.raw.RawGenerator;
import unalcol.search.space.Variation;

/**
 * <p>Title: SingleBitMutation</p>
 * <p>Description: Flips one bit in the chromosome. The flipped bit is randomly selected
 * with uniform probability distribution</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SingleBitMutation extends Variation<BitArray> {

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

 /**
  * Testing function
  */
  public static void main(String[] argv){
      System.out.println("*** Generating a genome of 21 genes randomly ***");
      BitArray genome = new BitArray(21, true);
      System.out.println(genome.toString());


      SingleBitMutation mutation = new SingleBitMutation();

      System.out.println("*** Applying the mutation ***");
      BitArray mutated = mutation.apply(genome);
      System.out.println("Mutated array " + mutated );
   }

}
