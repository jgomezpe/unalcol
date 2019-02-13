package unalcol.optimization.binary;

import unalcol.bit.Array;
import unalcol.integer.Uniform;
import unalcol.search.variation.Variation_1_1;

/**
 * <p>Title: Transposition</p>
 * <p>Description: The simple transposition operator (without flanking)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Transposition implements Variation_1_1<Array>{
	protected Uniform g = new Uniform(0);

	public Transposition(){}
    
  /**
   * Interchange the bits between two positions randomly chosen
   * Example:      genome = 100011001110
   * Transposition 2-10:    101100110010
   * @param _genome Genome to be modified
   */
    @Override
  public Array apply(Array _genome) {
      try{
          Array genome = new Array(_genome);

          g.set(genome.size());
          int start = g.next();
          int end = g.next();

          if (start > end) {
              int t = start;
              start = end;
              end = t;
          }
          boolean tr;

          while (start < end) {
              tr = genome.get(start);
              genome.set(start, genome.get(end));
              genome.set(end, tr);
              start++;
              end--;
          }
          return genome;
      }catch( Exception e ){}
      return null;
  }
}
