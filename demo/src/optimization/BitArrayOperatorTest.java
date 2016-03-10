package optimization;

import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.SingleBitMutation;
import unalcol.optimization.binary.XOver;
import unalcol.types.collection.bitarray.BitArray;

public class BitArrayOperatorTest {
	 /**
	  * Testing function
	  */
	  public static void bit_mutation(){
	    System.out.println("*** Generating a genome of 21 genes randomly ***");
	    BitArray genome = new BitArray(21, true);
	    System.out.println(genome.toString());

	    BitMutation mutation = new BitMutation(0.05);

	    System.out.println("*** Applying the mutation ***");
	    BitArray mutated = mutation.apply(genome);
	    System.out.println("Mutated array " + mutated );

	  }

	  /**
	   * Testing function
	   */
	   public static void single_bit_mutation(){
	       System.out.println("*** Generating a genome of 21 genes randomly ***");
	       BitArray genome = new BitArray(21, true);
	       System.out.println(genome.toString());


	       SingleBitMutation mutation = new SingleBitMutation();

	       System.out.println("*** Applying the mutation ***");
	       BitArray mutated = mutation.apply(genome);
	       System.out.println("Mutated array " + mutated );
	    }
	  
	   /**
	    * Testing function
	    */
	    public static void xover(){
	      System.out.println("*** Generating a genome of 20 genes randomly ***");
	      BitArray parent1 = new BitArray(20, true);
	      System.out.println(parent1);

	      System.out.println("*** Generating a genome of 20 genes randomly ***");
	      BitArray parent2 = new BitArray(20, true);
	      System.out.println(parent2);

	      XOver xover = new XOver();

	      System.out.println("*** Applying the croosover ***");
	      BitArray[] kids = xover.apply(parent1, parent2);

	      System.out.println("*** Child 1 ***");
	      System.out.println(kids[0]);
	      System.out.println("*** Child 2 ***");
	      System.out.println(kids[1]);
	    }
	   
	  public static void main( String[] args ){
		  bit_mutation();
		  single_bit_mutation();
		  xover();
	  }

}
