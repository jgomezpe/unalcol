package unalcol.optimization.binary.varlength;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.RawGeneratorWrapper;
import unalcol.search.variation.Variation_2_2;
import unalcol.services.AbstractMicroService;
import unalcol.services.MicroService;

/**
 * <p>Title: Join</p>
 * <p>Description: Joins two chromosomes</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Join extends MicroService<BitArray> implements Variation_2_2<BitArray>{
	public AbstractMicroService<?> wrap(String id){
		if(id.equals(RawGenerator.name)) return new RawGeneratorWrapper();
		return null;
	}

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public BitArray[] apply(BitArray c1, BitArray c2) {
      try {
          BitArray genome1 = new BitArray(c1);
          genome1.add(c2);
          BitArray genome2 = new BitArray(c2);
          genome2.add(c1);
          return new BitArray[]{genome1, genome2};
      } catch (Exception e) {
      }
      return null;
  }
}