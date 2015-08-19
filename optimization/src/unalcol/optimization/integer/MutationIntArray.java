package unalcol.optimization.integer;

import unalcol.io.Write;
import unalcol.random.integer.IntUniform;
import unalcol.random.util.*;
import unalcol.search.space.ArityOne;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;

/**
 * <p>Title: Mutation</p>
 * <p>Description: The simple bit mutation operator</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class MutationIntArray extends ArityOne<int[]> {
    
    protected IntUniform g;
  /**
   * Probability of mutating one single bit
   */
  protected double bit_mutation_rate = 0.0;

  /**
   * Constructor: Creates a mutation with a mutation probability depending on the size of the genome
   */
  public MutationIntArray( int MAX ) {
      g = new IntUniform(MAX);
  }

  /**
   * Constructor: Creates a mutation with the given mutation rate
   * @param bit_mutation_rate Probability of mutating each single bit
   */
  public MutationIntArray(double bit_mutation_rate, int MAX) {
	  this(MAX);
	  this.bit_mutation_rate = bit_mutation_rate;
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Number of mutated bits
   */
  @Override
  public int[] apply(int[] gen) {
      int[] genome = gen.clone();
      double rate = 1.0 - ((bit_mutation_rate == 0.0)?1.0/genome.length:bit_mutation_rate);
      RandBool gb = new RandBool(rate);
      for (int i = 0; i < genome.length; i++) {
        if(gb.next()) {
          genome[i] = g.next();
        }
      }
      return genome;
  }



 /**
  * Testing function
  */
  public static void main(String[] argv){
      IntArrayPlainWrite write = new IntArrayPlainWrite(',', false);
      Write.set(int[].class, write);

      System.out.println("*** Generating a genome of 21 genes randomly ***");
      int[] genome = IntArray.random(10, 10);
      System.out.println(Write.toString(genome));

      MutationIntArray mutation = new MutationIntArray(10);

      System.out.println("*** Applying the mutation ***");
      int[] mutated = mutation.apply(genome);
      System.out.println("Mutated array " + Write.toString(mutated) );
  }
}
