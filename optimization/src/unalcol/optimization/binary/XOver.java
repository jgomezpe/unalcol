package unalcol.optimization.binary;
import unalcol.clone.*;
import unalcol.random.raw.RawGenerator;
import unalcol.search.variation.Variation_2_2;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title: XOver</p>
 * <p>Description: The simple point crossover operator (variable length)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class XOver extends Variation_2_2<BitArray>{
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
  protected BitArray[] apply(BitArray child1, BitArray child2, int xoverPoint) {
      try{
          BitArray child1_1 = (BitArray) Clone.create(child1);
          BitArray child2_1 = (BitArray) Clone.create(child2);
          BitArray child1_2 = (BitArray) Clone.create(child1);
          BitArray child2_2 = (BitArray) Clone.create(child2);

          cross_over_point = xoverPoint;

          child1_2.leftSetToZero(cross_over_point);
          child2_2.leftSetToZero(cross_over_point);
          child1_1.rightSetToZero(cross_over_point);
          child2_1.rightSetToZero(cross_over_point);
          child1_2.or(child2_1);
          child2_2.or(child1_1);
          return new BitArray[]{child1_2, child2_2};
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
  public BitArray[] apply( BitArray child1, BitArray child2 ){
    RawGenerator g = RawGenerator.get(this);
    int pos = g.integer(Math.min(child1.size(), child2.size()));
//    System.out.println("pos-->"+pos);
    return apply(child1, child2, pos);
  }
}