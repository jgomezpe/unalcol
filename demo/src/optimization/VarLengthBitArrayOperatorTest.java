package optimization;

import unalcol.optimization.binary.varlength.AddGen;
import unalcol.optimization.binary.varlength.DelGen;
import unalcol.optimization.binary.varlength.Join;
import unalcol.types.collection.bitarray.BitArray;

public class VarLengthBitArrayOperatorTest {
	  public static void del_gen(){
	    System.out.println("*** Generating a genome of 27 genes randomly ***");
	    BitArray genome = new BitArray(27, true);
	    System.out.println(genome.toString());

	    System.out.println("*** Generating a Deletion Gen operation with gen length of 3 ***");
	    DelGen del = new DelGen(21, 27, 3);

	    System.out.println("*** Applying the deletion ***");
	    BitArray gene = del.apply(genome);

	    System.out.println("*** Mutated genome ***");
	    System.out.println(gene);

	  }
	
	  /**
	   * Testing function
	   * */
	   public static void add_gen(){
	     System.out.println("*** Generating a genome of 21 genes randomly ***");
	     BitArray genome = new BitArray(21, true);
	     System.out.println(genome.toString());

	     System.out.println("*** Generating a Addition Gen operation with gen length of 3 ***");
	     AddGen add = new AddGen(21, 27, 3);
	     System.out.println("*** Applying the addition ***");
	     BitArray gene = add.apply(genome);

	     System.out.println("*** Mutated genome ***");
	     System.out.println(gene);
	   }
	   
	   /**
	    * Testing function
	    */
	    public static void join(){
	      System.out.println("*** Generating a genome of 20 genes randomly ***");
	      BitArray parent1 = new BitArray(20, true);
	      System.out.println(parent1);

	      System.out.println("*** Generating a genome of 10 genes randomly ***");
	      BitArray parent2 = new BitArray(10, true);
	      System.out.println(parent2);

	      Join xover = new Join();

	      System.out.println("*** Applying the croosover ***");
	      BitArray child = xover.apply(parent1, parent2)[0];
	      System.out.println("New Individual " + child);
	    }

	   /**
	  * Testing function
	  */
	  public static void main(String[] argv){
		  del_gen();
		  add_gen();
		  join();
	  }
}
