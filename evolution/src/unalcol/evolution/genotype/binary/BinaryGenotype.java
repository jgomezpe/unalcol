package unalcol.evolution.genotype.binary;
import unalcol.evolution.Genotype;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title:  BinaryGenotype</p>
 * <p>Description: A Binary encoding mechanism</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class BinaryGenotype  extends Genotype<BitArray> {
  /**
   * Lenght of the new bitarray
   */
  protected int length = 0;

  /**
   * Creates a BinaryGenotype with 0 lenght
   */
  public BinaryGenotype() {

  }

  /**
   * Creates a BinaryGenotype with the given lenght
   * @param length The lengh of the new bitarray
   */
  public BinaryGenotype(int length) {
    setSize( length );
  }

  /**
   * Creates a new genome of the binary genotype
   * @return Object New binary genome
   */
  public BitArray get() {
    return new BitArray(length, true);
  }

  /**
   * Returns the number of bits of the genome
   * @return Number of bits of the chromosome
   */
  public int size(BitArray genome) {
    return genome.size();
  }

  public Object owner(){
      return BitArray.class;
  }

  public void setSize( int length ){
      this.length = length;
  }
}