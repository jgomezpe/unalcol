package optimization;

import unalcol.io.Write;
import unalcol.integer.Mutation;
import unalcol.integer.XOver;
import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.integer.array.Array;
import unalcol.integer.array.PlainWrite;

public class IntArrayOperatorTest {
	public static void init_services(){
		Service.register(new JavaGenerator(), Object.class);         
		Service.register(new PlainWrite(',',false), int[].class);
	}
	 /**
	  * Testing function
	  */
	  public static void mutation(){

	      System.out.println("*** Generating a genome of 21 genes randomly ***");
	      int[] genome = Array.random(10, 10);
	      System.out.println(Write.toString(genome));

	      Mutation mutation = new Mutation(10);

	      System.out.println("*** Applying the mutation ***");
	      int[] mutated = mutation.apply(genome);
	      System.out.println("Mutated array " + Write.toString(mutated) );
	  }

	public static void xover(){
	      System.out.println("*** Generating a genome of 20 genes randomly ***");
	      int D = 1000;
	      int MAX = 1000;
	      int[] parent1 = Array.random(D,MAX);
	      System.out.println(Write.toString(parent1));
		
	      System.out.println("*** Generating a genome of 10 genes randomly ***");
	      int[] parent2 = Array.random(D,MAX);
	      System.out.println(Write.toString(parent2));
		
	      XOver xover = new XOver();
		
	      System.out.println("*** Applying the croosover ***");
	      int[][] children = xover.apply(parent1, parent2);
		
	      System.out.println("*** Child 1 ***");
	      System.out.println(Write.toString(children[0]));
	      System.out.println("*** Child 2 ***");
	      System.out.println(Write.toString(children[1]));
	  }
	
	 /**
	  * Testing function
	  */
	  public static void main(String[] argv){
		  init_services();
		  mutation();
		  xover();
	  }
}
