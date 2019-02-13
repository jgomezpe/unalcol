package optimization;

import unalcol.optimization.binary.mutation.BitMutation;
import unalcol.optimization.binary.mutation.SingleBitMutation;
import unalcol.optimization.binary.XOver;
import unalcol.collection.bitarray.BitArray;

public class BitArrayOperatorTest {
	 /**
	  * Testing function
	  */
	  public static void bit_mutation(){
	    System.out.println("*** Bit Mutation: Generating a genome of 21 genes randomly ***");
	    BitArray genome = new BitArray(21, true);
	    BitMutation mutation = new BitMutation(0.05);
	    BitArray mutated = mutation.apply(genome);
	    System.out.println(genome+"<--Original");
	    System.out.println(mutated+"<--Mutated");
	  }

	  /**
	   * Testing function
	   */
	   public static void single_bit_mutation(){
		    System.out.println("*** Single Bit Mutation: Generating two genomes of 20 genes randomly ***");
		    BitArray genome = new BitArray(21, true);
		    SingleBitMutation mutation = new SingleBitMutation();
		    BitArray mutated = mutation.apply(genome);
		    System.out.println(genome+"<--Original");
		    System.out.println(mutated+"<--Mutated");
	    }
	  
	   /**
	    * Testing function
	    */
	    public static void xover(){
	      System.out.println("***  XOver: Generating two genomes of 20 genes randomly ***");
	      BitArray parent1 = new BitArray(20, true);
	      BitArray parent2 = new BitArray(20, true);
	      System.out.println(parent1+":"+parent2+"<--parents");

	      XOver xover = new XOver();
	      BitArray[] kids = xover.apply(parent1, parent2);
	      System.out.println(kids[0]+":"+kids[1]+"<--offspring");
	    }
	   
	  public static void main( String[] args ){
		  bit_mutation();
		  single_bit_mutation();
		  xover();
	  }

}
