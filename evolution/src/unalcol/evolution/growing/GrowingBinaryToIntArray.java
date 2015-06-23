package unalcol.evolution.growing;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.types.collection.bitarray.*;

/**
 * <p>Title:GrowingBinaryToIntArray</p>
 * <p>Description: Growing function that creates an array of integers from a bit array</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GrowingBinaryToIntArray extends CodeDecodeMap<BitArray,int[]> {
	/**
	 * If grayCode is used
	 */
  protected boolean grayCode;
  /**
   * int size
   */
  protected int int_size;

  /**
   * Constructor: Creates an individual with a random genome
   */
  public GrowingBinaryToIntArray(int _int_size, boolean _grayCode) {
    grayCode = _grayCode;
    int_size = _int_size;
  }

  /**
   * Constructor: Creates an individual with a random genome
   */
  public GrowingBinaryToIntArray(int _int_size) {
    grayCode = false;
    int_size = _int_size;
  }

  /**
   * Generates a thing from the given genome
   * @param genome Genome of the thing to be expressed
   * @return A thing expressed from the genome
   */
  public int[] decode(BitArray genome) {
    return BitArrayConverter.getIntArray(genome, int_size, grayCode);
  }

  /**
   * Generates a genome from the given thing
   * @param thing A thing expressed from the genome
   * @return Genome of the thing
   */
  public BitArray code(int[] thing) {
    BitArray A = new BitArray(int_size*thing.length, false);
    BitArrayConverter.setIntArray(A, thing, int_size, grayCode);
    return A;
  }
  
  public int min(){
      if( int_size == Integer.SIZE ){
          return Integer.MIN_VALUE;
      }else{
          return 0;
      }
  }
  
  public int max(){
      if( int_size >= Integer.SIZE ){
          return Integer.MAX_VALUE;
      }else{
          return (1 << int_size) - 1;
      }
  } 
  
  public static void main( String[] args ){
      for( int i=1; i<=32; i++ ){
          GrowingBinaryToIntArray grow = new GrowingBinaryToIntArray(i);
          System.out.println( i + ","+ grow.min() + "," + grow.max() );
      }
  }
}
