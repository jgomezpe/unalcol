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
    
  /**
   * Creates a BinaryGenotype with the given lenght
   * @param length The length of the new bitarray
   */
  public BitArrayInstance() {
  }

  /**
   * Creates a new genome of the binary genotype
   * @return Object New binary genome
   */
  public BitArray get( int n ) {
    return new BitArray(n, true);
  }
  
  /**
   * Creates a new genome of the binary genotype
   * @return Object New binary genome
   */
  @Override
  public BitArray get( Object... n ) {
    return get( (Integer)n[0] );
  }

  @Override
  public Class<?> type() {
	return BitArray.class;
  }   
}