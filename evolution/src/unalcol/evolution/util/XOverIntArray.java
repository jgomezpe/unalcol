/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.util;

import unalcol.optimization.operators.ArityTwo;
import unalcol.random.Random;
import unalcol.types.collection.vector.Vector;

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
      try{
          int[] child1_1 = child1.clone();
          int[] child2_1 = child2.clone();

          cross_over_point = xoverPoint;

          for(int i=xoverPoint;i<child1.length; i++){
              child1_1[i] = child2[i];
              child2_1[i] = child1[i];              
          }
          
          Vector v = new Vector();
          v.add(child1_1);
          v.add(child2_1);
          return v;
      }catch( Exception e ){}
      return null;
  }

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param child1 The first parent
   * @param child2 The second parent
   * @return The crossover point
   */
  @Override
  public Vector<int[]> generates( int[] child1, int[] child2 ){
    return generates(child1, child2, Random.nextInt(Math.min(child1.length, child2.length)));
  }

   /**
   * Class of objects the operator is able to process
   * @return Class of objects the operator is able to process
   */
  @Override
  public Object owner(){
      return int[].class;
  }

  public static String toStringInt( int[] a ){
    StringBuilder sb = new StringBuilder();
    for( int i=0; i<a.length; i++){
        sb.append(' ');
        sb.append(a[i]);
    }
    return sb.toString();
  }
  
  public static int[] create( int D, int MAX ){
      int[] a = new int[D];
      for( int i=0; i<D; i++){
          a[i] = (int)(Math.random() * MAX);
      }
      return a;
  }
 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 20 genes randomly ***");
    int D = 1000;
    int MAX = 1000;
    int[] parent1 = create(D,MAX);
    System.out.println(toStringInt(parent1));

    System.out.println("*** Generating a genome of 10 genes randomly ***");
    int[] parent2 = create(D,MAX);
    System.out.println(toStringInt(parent2));

    XOverIntArray xover = new XOverIntArray();

    System.out.println("*** Applying the croosover ***");
    Vector<int[]> children = xover.generates(parent1, parent2);

    System.out.println("*** Child 1 ***");
    System.out.println(toStringInt(children.get(0)));
    System.out.println("*** Child 2 ***");
    System.out.println(toStringInt(children.get(1)));
  }
}
