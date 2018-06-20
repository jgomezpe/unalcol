package unalcol.optimization.binary;

import unalcol.types.collection.bitarray.BitArray;
import unalcol.random.integer.IntUniform;
import unalcol.search.variation.Variation_1_1;

/**
 * <p>Title: SingleBitMutation</p>
 * <p>Description: Flips one bit in the chromosome. The flipped bit is randomly selected
 * with uniform probability distribution</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SingleBitMutation implements Variation_1_1<BitArray> {
	protected IntUniform g = new IntUniform(0);
	/**
	 * Flips a bit in the given genome
	 * @param genome Genome to be modified
	 * @return Index of the flipped bit
	 */
	public BitArray apply(BitArray genome) {
		genome = new BitArray(genome);
		int pos = -1;
		try{
			g.set(genome.size());
			pos = g.next();
			genome.not(pos);
		}catch(Exception e){ System.err.println("[Mutation]" + e.getMessage()); }          
		return null;
	}
}