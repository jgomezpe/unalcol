package unalcol.optimization.binary.varlength;
import unalcol.search.population.variation.ArityTwo;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.Vector;
import unalcol.clone.*;

/**
 * <p>Title: Join</p>
 * <p>Description: Joins two chromosomes</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Join extends ArityTwo<BitArray>{

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public Vector<BitArray> apply(BitArray c1, BitArray c2) {
      try {
          BitArray genome = (BitArray) Clone.create(c1);
          genome.add(c2);
          Vector<BitArray> v = new Vector<BitArray>();
          v.add(genome);
          return v;
      } catch (Exception e) {
      }
      return null;
  }


 /**
  * Testing function
  */
  public static void main(String[] argv){
    System.out.println("*** Generating a genome of 20 genes randomly ***");
    BitArray parent1 = new BitArray(20, true);
    System.out.println(parent1.toString());

    System.out.println("*** Generating a genome of 10 genes randomly ***");
    BitArray parent2 = new BitArray(10, true);
    System.out.println(parent2.toString());

    Join xover = new Join();

    System.out.println("*** Applying the croosover ***");
    BitArray child = xover.apply(parent1, parent2).get(0);
    System.out.println("New Individual " + child);

  }
}
