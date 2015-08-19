/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.integer;

import unalcol.io.Write;
import unalcol.random.raw.RawGenerator;
import unalcol.search.population.variation.ArityTwo;
import unalcol.types.collection.vector.Vector;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;

/**
 *
 * @author Jonatan
 */
public class XOverIntArray  extends ArityTwo<int[]> {
    public XOverIntArray(){}

    
  /**
   * The crossover point of the last xover execution
   */
  protected int cross_over_point;

  /**
   * Apply the simple point crossover operation over the given genomes at the given
   * cross point
   * @param child1 The first parent
   * @param child2 The second parent
   * @param xoverPoint crossover point
   * @return The crossover point
   */
  public Vector<int[]> generates(int[] child1, int[] child2, int xoverPoint) {
      int[] child1_1 = child1.clone();
      int[] child2_1 = child2.clone();

      cross_over_point = xoverPoint;

      for(int i=xoverPoint;i<child1.length; i++){
          child1_1[i] = child2[i];
          child2_1[i] = child1[i];              
      }
      
      Vector<int[]> v = new Vector<int[]>();
      v.add(child1_1);
      v.add(child2_1);
      return v;
  }

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param child1 The first parent
   * @param child2 The second parent
   * @return The crossover point
   */
  @Override
  public Vector<int[]> apply( int[] child1, int[] child2 ){
    return generates(child1, child2, RawGenerator.integer(this, Math.min(child1.length, child2.length)));
  }

 /**
  * Testing function
  */
  public static void main(String[] argv){
      IntArrayPlainWrite write = new IntArrayPlainWrite(',', false);
      Write.set(int[].class, write);
      System.out.println("*** Generating a genome of 20 genes randomly ***");
      int D = 1000;
      int MAX = 1000;
      int[] parent1 = IntArray.random(D,MAX);
      System.out.println(Write.toString(parent1));
	
      System.out.println("*** Generating a genome of 10 genes randomly ***");
      int[] parent2 = IntArray.random(D,MAX);
      System.out.println(Write.toString(parent2));
	
      XOverIntArray xover = new XOverIntArray();
	
      System.out.println("*** Applying the croosover ***");
      Vector<int[]> children = xover.apply(parent1, parent2);
	
      System.out.println("*** Child 1 ***");
      System.out.println(Write.toString(children.get(0)));
      System.out.println("*** Child 2 ***");
      System.out.println(Write.toString(children.get(1)));
  }
}
