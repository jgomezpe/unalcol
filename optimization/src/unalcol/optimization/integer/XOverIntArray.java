/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.integer;

import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.RawGeneratorWrapper;
import unalcol.search.variation.Variation_2_2;
import unalcol.services.AbstractMicroService;
import unalcol.services.MicroService;

/**
 *
 * @author Jonatan
 */
public class XOverIntArray extends MicroService<int[]> implements Variation_2_2<int[]>{
	public AbstractMicroService<?> wrap(String id){
		if(id.equals(RawGenerator.name)) return new RawGeneratorWrapper();
		return null;
	}

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
  public int[][] generates(int[] child1, int[] child2, int xoverPoint) {
      int[] child1_1 = child1.clone();
      int[] child2_1 = child2.clone();

      cross_over_point = xoverPoint;

      for(int i=xoverPoint;i<child1.length; i++){
          child1_1[i] = child2[i];
          child2_1[i] = child1[i];              
      }
      
      return new int[][]{child1_1,child2_1};
  }

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param child1 The first parent
   * @param child2 The second parent
   * @return The crossover point
   */
  @Override
  public int[][] apply( int[] child1, int[] child2 ){
    return generates(child1, child2, ((RawGenerator)getMicroService(RawGenerator.name)).integer(Math.min(child1.length, child2.length)));
  }

}
