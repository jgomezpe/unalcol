package unalcol.optimization.binary;
import unalcol.bit.Array;
import unalcol.integer.Uniform;
import unalcol.search.variation.Variation_2_2;

/**
 * <p>Title: XOver</p>
 * <p>Description: The simple point crossover operator (variable length)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class XOver implements Variation_2_2<Array>{

	protected Uniform g = new Uniform(0);
    
	public XOver(){}

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
  protected Array[] apply(Array child1, Array child2, int xoverPoint) {
      try{
          Array child1_1 = new Array(child1);
          Array child2_1 = new Array(child2);
          Array child1_2 = new Array(child1);
          Array child2_2 = new Array(child2);

          cross_over_point = xoverPoint;

          child1_2.leftSetToZero(cross_over_point);
          child2_2.leftSetToZero(cross_over_point);
          child1_1.rightSetToZero(cross_over_point);
          child2_1.rightSetToZero(cross_over_point);
          child2_1.or(child1_2);
          child1_1.or(child2_2);
          return new Array[]{child1_1, child2_1};
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
  public Array[] apply( Array child1, Array child2 ){
    g.set(Math.min(child1.size(), child2.size())); 
    return apply(child1, child2, g.next());
  }    
}