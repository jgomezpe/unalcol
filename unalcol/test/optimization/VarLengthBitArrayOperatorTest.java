package optimization;

import unalcol.bit.Array;
import unalcol.optimization.binary.varlength.AddGen;
import unalcol.optimization.binary.varlength.DelGen;
import unalcol.optimization.binary.varlength.Join;

public class VarLengthBitArrayOperatorTest {
	  public static void del_gen(){
	    System.out.println("*** Generating a genome of 27 genes randomly ***");
	    Array genome = new Array(27, true);
	    System.out.println(genome.toString());

	    System.out.println("*** Generating a Deletion Gen operation with gen length of 3 ***");
	    DelGen del = new DelGen(21, 27, 3);

	    System.out.println("*** Applying the deletion ***");
	    Array gene = del.apply(genome);

	    System.out.println("*** Mutated genome ***");
	    System.out.println(gene);

	  }
	
	  /**
	   * Testing function
	   * */
	   public static void add_gen(){
	     System.out.println("*** Generating a genome of 21 genes randomly ***");
	     Array genome = new Array(21, true);
	     System.out.println(genome.toString());

	     System.out.println("*** Generating a Addition Gen operation with gen length of 3 ***");
	     AddGen add = new AddGen(21, 27, 3);
	     System.out.println("*** Applying the addition ***");
	     Array gene = add.apply(genome);

	     System.out.println("*** Mutated genome ***");
	     System.out.println(gene);
	   }
	   
	   /**
	    * Testing function
	    */
	    public static void join(){
	      System.out.println("*** Generating a genome of 20 genes randomly ***");
	      Array parent1 = new Array(20, true);
	      System.out.println(parent1);

	      System.out.println("*** Generating a genome of 10 genes randomly ***");
	      Array parent2 = new Array(10, true);
	      System.out.println(parent2);

	      Join xover = new Join();

	      System.out.println("*** Applying the croosover ***");
	      Array child = xover.apply(parent1, parent2)[0];
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
