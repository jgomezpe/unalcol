package unalcol.bit;

import unalcol.bit.Array;
import unalcol.integer.Util;

/**
 * <p>Title: BitArrayConverter</p>
 * <p>Description: Utility class for converting BitArrays to/from IntArrays.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class Converter {
  public static boolean useGrayCode = false;

   /**
    * Returns an array of int values
    * @param a Original set of bits
    * @param intSize size of the array
    * @param useGray treatment type to use
    * @return array of int values
    */
   public static int[] getIntArray(Array a, int intSize, boolean useGray) {
    int n = a.size() / intSize;
    int[] intVal = new int[n];
    if (intSize == Util.INTEGER_SIZE) {
      for (int i = 0; i < n; i++) {
        if (useGray) { intVal[i] = Util.grayToBinary(a.getData()[i]);
        } else { intVal[i] = a.getData()[i]; }
      }
    } else {
      int start = 0;
      for (int i = 0; i < n; i++) {
        intVal[i] = getNumber(a, start, intSize);
        start += intSize;
      }
    }
    return intVal;
  }

  /**
   * Convert an array of BitArray into an byte[]
   * @param a Original set of bits
   * @return array of Bit
   */
  public static byte[] getByteArray(Array a) {
    int byteSize = 8;
    int maxByte = 255;
    int pack = Util.INTEGER_SIZE / byteSize;
    int nInt = (a.size() + Util.INTEGER_SIZE - 1) / Util.INTEGER_SIZE;
    int n = pack * nInt;
    byte[] byteArray = new byte[n];
    int k = 0;
    for (int i = 0; i < nInt; i++) {
      int d = a.getInt(i);
      for (int j = 0; j < pack; j++) {
        byteArray[k] = (byte) (d & maxByte);
        d >>>= byteSize;
        k++;
      }
    }
    return byteArray;
  }

  /**
   * Returns an array of int values
   * @param a Original set of bits
   * @return array of int values
   */
  public static int[] getIntArray(Array a) {
    return getIntArray(a, Util.INTEGER_SIZE, useGrayCode);
  }

  /**
   * it assigns an array inside an it BitArray
   * @param a Original set of bits
   * @param intVal array of int
   * @param intSize size of the array
   * @param useGray treatment type to use
   */
  public static void setIntArray(Array a, int[] intVal, int intSize, boolean useGray) {
    int n = intVal.length;
    if (intSize == Util.INTEGER_SIZE) {
      if (a.getData().length < n) {
        a.setData(new int[n]);
      }
      for (int i = 0; i < n; i++) {
        if (useGray) {
          a.getData()[i] = Util.binaryToGray(intVal[i]);
        } else {
          a.getData()[i] = intVal[i];
        }
      }
    } else {
      n = (n * intSize) / Util.INTEGER_SIZE + 1;
      a.setData(new int[n]);
      int start = 0;
      for (int i = 0; i < n; i++) {
        setNumber(a, start, intSize, intVal[i]);
        start += intSize;
      }
    }
  }


  /**
     * Return the integer encoded by the "length" bits starting at the position start
     * @param a Original set of bits
     * @param start Start position of the subset of bits
     * @param length Number of bits to be extracted
     * @return Integer encoded by the "length" bits starting at the position start
     */
  public static int getNumber(Array a, int start, int length) {
    Array b = a.subBitArray(start, start + length);
    return (b.getInt(0) >>> (Util.INTEGER_SIZE - length));
  }

  /**
   * Assigns the value number in the defined range
   * @param a Object BitArray
   * @param start Initial range
   * @param length Final range
   * @param number Value to replace
   */
  public static void setNumber(Array a, int start, int length, int number) {
    int n = start + length - 1;
    for (int i = 0; i < length; i++) {
      a.set(n, ((number & 1) == 1));
      number = number >>> 1;
      n--;
    }
  }

}
