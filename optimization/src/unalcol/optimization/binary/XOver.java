package unalcol.optimization.binary;
import unalcol.clone.*;
import unalcol.random.raw.RawGenerator;
import unalcol.search.population.variation.ArityTwo;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.*;

/**
 * <p>Title: XOver</p>
 * <p>Description: The simple point crossover operator (variable length)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class XOver extends ArityTwo<BitArray> {
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
  public Vector<BitArray> apply(BitArray child1, BitArray child2, int xoverPoint) {
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
          Vector<BitArray> v = new Vector<BitArray>();
          v.add(child1_2);
          v.add(child2_2);
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
  public Vector<BitArray> apply( BitArray child1, BitArray child2 ){
    RawGenerator g = RawGenerator.get(this);
    return apply(child1, child2, g.integer(Math.min(child1.size(), child2.size())));
  }

 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 20 genes randomly ***");
    BitArray parent1 = new BitArray(20, true);
    System.out.println(parent1.toString());

    System.out.println("*** Generating a genome of 20 genes randomly ***");
    BitArray parent2 = new BitArray(20, true);
    System.out.println(parent2.toString());

    XOver xover = new XOver();

    System.out.println("*** Applying the croosover ***");
    Vector<BitArray> kids = xover.apply(parent1, parent2);

    System.out.println("*** Child 1 ***");
    System.out.println(kids.get(0).toString());
    System.out.println("*** Child 2 ***");
    System.out.println(kids.get(1).toString());

  }
}
