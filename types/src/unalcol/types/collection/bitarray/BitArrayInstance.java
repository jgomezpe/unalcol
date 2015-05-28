/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.bitarray;

import unalcol.instance.Instance;

/**
 *
 * @author jgomez
 */
public class BitArrayInstance extends Instance<BitArray> {
    
  static{
      register(BitArray.class, new BitArrayInstance());
  }  

  /**
   * Creates a BinaryGenotype with the given lenght
   * @param length The lengh of the new bitarray
   */
  public BitArrayInstance() {
  }

  /**
   * Creates a new genome of the binary genotype
   * @return Object New binary genome
   */
  @Override
  public BitArray get( BitArray array ) {
    return new BitArray(array.dimension(), true);
  }
}
