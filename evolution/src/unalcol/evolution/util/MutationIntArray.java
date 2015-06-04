package unalcol.evolution.util;

import unalcol.optimization.operators.ArityOne;
import unalcol.optimization.operators.binary.Mutation;
import unalcol.random.util.*;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.*;

/**
 * <p>Title: Mutation</p>
 * <p>Description: The simple bit mutation operator</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class MutationIntArray extends ArityOne<int[]> {
    protected int MAX;
  /**
   * Probability of mutating one single bit
   */
  protected double bit_mutation_rate = 0.0;

  /**
   * Constructor: Creates a mutation with a mutation probability depending on the size of the genome
   */
  public MutationIntArray( int MAX ) {
      this.MAX = MAX;
  }


   /**
   * Class of objects the operator is able to process
   * @return Class of objects the operator is able to process
   */
  @Override
  public Object owner(){
      return int[].class;
  }
  
  /**
   * Constructor: Creates a mutation with the given mutation rate
   * @param bit_mutation_rate Probability of mutating each single bit
   */
  public MutationIntArray(double bit_mutation_rate, int MAX) {
    this.bit_mutation_rate = bit_mutation_rate;
    this.MAX = MAX;
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Number of mutated bits
   */
  @Override
  public Vector<int[]> generates(int[] gen) {
    try{
      int[] genome = gen.clone();
      int count = 0;
      double rate = 1.0 - ((bit_mutation_rate == 0.0)?1.0/genome.length:bit_mutation_rate);
      BooleanGenerator g = new BooleanGenerator(rate);
      for (int i = 0; i < genome.length; i++) {
        if(g.next()) {
          genome[i] = (int)Math.random()*MAX;
          count++;
        }
      }
      Vector<int[]> v = new Vector<int[]>();
      v.add(genome);
      return v;
    }catch( Exception e ){ 
        e.printStackTrace();
        System.err.println("[Mutation]"+e.getMessage()); }
    return null;
  }



 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 21 genes randomly ***");
    int[] genome = XOverIntArray.create(10, 10);
    System.out.println(XOverIntArray.toStringInt(genome));

    MutationIntArray mutation = new MutationIntArray(10);

    System.out.println("*** Applying the mutation ***");
    int[] mutated = mutation.generates(genome).get(0);
    System.out.println("Mutated array " + XOverIntArray.toStringInt(mutated) );

  }

}
