package unalcol.bit;
import unalcol.integer.Util;
import unalcol.integer.Uniform;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

/**
 * <p>Title: BitArray</p>
 * <p>Description: Stores the positions with a value different of the default value,
 * the values are bits</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class Array implements Cloneable {
  /**
   * Integer array used to store the bits
   */
 private int[] data = null;
  /**
   * The number of bits in the bit array
   */
  private int n = 0;

  /**
   * Constructor: Creates a bit array of n bits, in a random way or with all bit in false according to the randomly argument
   * @param n The size of the bit array
   * @param randomly If the array will be initialized randomly or not
   */
  public Array(int n, boolean randomly) {
    this.n = n;
    int m = getIndex(n) + 1;
    data = new int[m];
    if (randomly){
      Uniform g = new Uniform(Util.HIGHEST_BIT >>> 1);
      Rand rg = new Rand();
      g.generate( data, 0, m );
      for (int i = 0; i < m; i++) {
        if(rg.next()){ data[i] = -data[i]; }
      }
    } else {
      for (int i = 0; i < m; i++) {
        data[i] = 0;
      }
    }
  }

  /**
   * Constructor: Creates a clone of the bit array given as argument
   * @param source The bit array that will be cloned
   */
  public Array(Array source) {
    if (source.data != null) {
      n = source.n;
      data = source.data.clone();
    }
  }

  /**
   * Constructor: Creates a bit array using the boolean values given in the array
   * @param source The bits that will conform the bit array
   */
  public Array (boolean[] source) {
    n = source.length;
    int m = getIndex(n) + 1;
    data = new int[m];
    for (int i = 0; i < n; i++) {
      set(i, source[i]);
    }
  }

  /**
   * Constructor: Creates a bit array using the boolean values given in the string
   * @param source The String with the bits that will conform the bit array
   */
  public Array(String source) {
    n = source.length();
    int m = getIndex(n) + 1;
    data = new int[m];
    for (int i = 0; i < n; i++) {
      set(i, (source.charAt(i) == '1'));
    }
  }


  /**
   * Utilizada para retornar un BitArray
   * @param limit Utilizado para asignarle el tamano al BitArray con el numero de bit requeridos para represnetar su valor
   * @return BitArray de tamano igual al numero de bit para representar limit y contiene 0 y 1 aleatorios
   */
  public static Array random(int limit) {
    int size = Util.getBitsNumber(limit);
    return new Array(size, true);
  }


  /**
   * Utilizada para clonar un BitArray, sin copiar su referencia
   * @return The new BitArray
   */
  public Object clone() {
    return new Array(this);
  }

  /**
   * Returns the buffer position (the integer that contains the bit) of an specific bit
   * <p>  m DIV INTEGER_SIZE </P>
   * @param m The bit index
   * @return The buffer position of an specific bit
   */
  private int getIndex (int  m) {
    return (m >>> Util.DIV_MASK);
  }

  /**
   * Returns the position of a specific bit in the integer that contains it.
   * <p>  m MOD INTEGER_SIZE </p>
   * @param m The bit index
   * @return The position of a specific bit in the integer that contains it
   */
  private int getBit(int m) {
    return (m & Util.MOD_MASK);
  }

  /**
   * Sets a bit to a given value
   * @param i The bit index
   * @param v The new value for the bit
   */
  public void set(int i, boolean v) {
    int m = getIndex(i);
    int p = getBit(i);
    int vmask = (Util.HIGHEST_BIT >>> p);
    int dmask = vmask & data[m];
    if (v) {
      if (dmask == 0) { data[m] |= vmask; };
    } else {
      if (dmask != 0) { data[m] ^= vmask; }
    }
  }

  /**
   * Returns the boolean value of a specific position
   * @param i The bit index
   * @return The boolean value of a specific position
   */
  public boolean get(int i) throws ParamsException{
    int m = getIndex(i);
    int p = getBit(i);
    try{  return (((Util.HIGHEST_BIT >>> p) & data[m]) != 0); }catch(Exception e ){ throw new ParamsException(InnerCore.NOSUCHELEM, i); }
  }

  /**
   * Returns a sub bit array of the bit array starting from the position start until the end of the bit array.
   * <p>  A = 1000111001</p>
   * <p>  A.subBitArray( 4 ) = 111001</p>
   * <p>  A.subArray( 0 ) = 1000111001</p>
   * <p>  A.subArray( 10 ) = empty bit array</p>
   * @param start The start position
   * @return A sub bit array of the bit array starting from the position start until the end of the bit array.
   */
  public Array subBitArray(int start) {
    Array subArray = new Array(this);
    subArray.leftShift(start);
    subArray.n -= start;
    if (subArray.n < 0) { subArray.n = 0; }
    return subArray;
  }

  /**
   * Returns the sub bit array of the bit array starting at the position start and the previous to the position end-1.
   * If the end position is greater than the last position of the array then the function will return only the last bits.
   * <p>  A = 1000111001</p>
   * <p>  A.subBitArray( 4, 7 ) = 111</p>
   * <p>  A.subArray( 0, 4 ) = 1000</p>
   * <p>  A.subArray( 7, 11 ) = 001</p>
   * @param start The start position of the substring
   * @param end The end position + 1 of the subarray
   * @return The sub bit array of the bit array starting at the position start and the previous to the position end-1.
   */
  public Array subBitArray(int start, int end) {
    int length = end - start;
    Array subArray = subBitArray(start);
    if (subArray.n > length) { subArray.n = length; };
    subArray.rightSetToZero(subArray.n);
    return subArray;
  }

  /**
   * Shifts the bit array to the left in k bits. The remaining bits will be set to zero.
   * <p>  A = 1010011011</p>
   * <p>  A.left_shift( 3 ) = 0011011000</p>
   * @param k The number of bits to be shifted
   */
  public void leftShift(int k) {
    if (data != null) {
      rightSetToZero(n);
      int startindex = getIndex(k);
      k = getBit(k);
      if (k > 0) {
        int supk = Util.INTEGER_SIZE - k;
        int m = data.length - 1;
        int j = 0;
        for (int i = startindex; i < m; i++) {
          data[j] = (data[i] << k) | (data[i + 1] >>> supk);
          j++;
        }
        data[j] = (data[m] << k);
        for (int i = j + 1; i < m; i++) { data[i] = 0; }
      } else {
        int j = 0;
        for (int i = startindex; i < data.length; i++) {
          data[j] = data[i];
          j++;
        }
        for (int i = j; i < data.length; i++) { data[i] = 0; }
      }
    }
  }

  /**
   * Shifts the bit array to the right in k bits. The remaining bits will be set to zero
   * <p>  A = 1010011011</p>
   * <p>  A.right_shift( 3 ) = 0001010011</p>
   * @param k The number of bits to be shifted
   */
  public void rightShift(int k) {
    if (data != null) {
      rightSetToZero(n);
      int startindex = getIndex(k);
      k = getBit(k);
      if (k > 0) {
        int supk = Util.INTEGER_SIZE - k;
        //int m = data.length - 1;
        int j = data.length - 1 - startindex;
        for (int i = data.length - 1; i > startindex; i--) {
          data[i] = (data[j] >>> k) | (data[j - 1] << supk);
          j--;
        }
        data[startindex] = (data[0] >>> k);
        for (int i = 0; i < startindex; i++) { data[i] = 0; }
      } else {
        int j = data.length - 1 - startindex;
        for (int i = data.length - 1; i >= startindex; i--) {
          data[i] = data[j];
          j--;
        }
        for (int i = 0; i < startindex; i++) { data[i] = 0; }
      }
    }
  }

  /**
   * Add the given bit array to the end of the bit array.
   * <p>  A = 1001</p>
   * <p>  B = 11001</p>
   * <p>  A.add( B ) = 100111001</p>
   * @param source The bit array to be added to the end
   */
  public void add(Array source) {
    Array newBitArray = new Array (n + source.n, false);
    newBitArray.or(source);
    newBitArray.rightShift(n);
    newBitArray.or(this);
    data = newBitArray.data;
    n = newBitArray.n;
  }

  /**
   * Add the given bit to the end of the bit array
   * @param v The bit to be added
   */
  public void add(boolean v) {
    if (data.length * Util.INTEGER_SIZE == n) {
      int[] newdata = new int[data.length + 1];
      for (int i = 0; i < data.length; i++) {
        newdata[i] = data[i];
      }
      if (v) { newdata[data.length] = Util.HIGHEST_BIT;
      } else { newdata[data.length] = 0; }
      data = newdata;
    } else {
      set(n, v);
    }
    n++;
  }

  /**
   * Removes the last bit in the bit array
   */
  public void del() {
   del(1);
  }

  /**
   * Removes the last k bits from the bit array
   * @param k the number of bits to be removed
   */
  public void del(int k) {
    n -= k;
    if (n > 0) {
      int m = getIndex(n);
      if (m + 1 < data.length) {
        int[] newdata = new int[m + 1];
        for (int i = 0; i <= m; i++) {
          newdata[i] = data[i];
        }
        data = newdata;
      }
    } else {
      n = 0;
      data = new int[1];
    }
  }

  /**
   * Returns the number of bits in the array
   * @return The number of bits in the array
   */
  public int size() { return n; }

  /**
   * Sets all the bits in the array to zero
   */
  public void zero() {
    for (int i = 0; i < data.length; i++) {
      data[i] = 0;
    }
  }

  /**
   * Set the last bits to zero starting to the given position.
   * <p> A = 1000111011</p>
   * <p> A.rightSetToZero( 6 ) = 1000110000</p>
   * @param start The start position to be set to zero
   */
  public void rightSetToZero(int start) {
    int m = getIndex(start);
    if (data != null && 0 <= m && m < data.length) {
      int r = getBit(start);
      int mask;
      if (r > 0) { mask = Util.ONE_BITS << (Util.INTEGER_SIZE - r);
      } else { mask = 0; }
      data[m] &= mask;
      for (int i = m + 1; i < data.length; i++) {
        data[i] = 0;
      }
    }
  }

  /**
   * Set the last bits to one starting to the given position.
   * <p> A = 1000110011</p>
   * <p> A.rightSetToOne( 6 ) = 1000111111</p>
   * @param start The start position to be set to one
   */
  public void rightSetToOne(int start) {
    int m = getIndex(start);
    if (0 <= m && m < data.length) {
      int r = getBit(start);
      int mask = Util.ONE_BITS >>> r;
      data[m] |= mask;
      for (int i = m + 1; i < data.length; i++) {
        data[i] = Util.ONE_BITS;
      }
    }
  }


  /**
   * Set the first bits to zero.
   * <p> A = 1000111011</p>
   * <p> A.leftSetToZero( 6 ) = 0000001011</p>
   * @param end The number of bits to be set to zero
   */
  public void leftSetToZero(int end) {
    int m = getIndex(end);
    if (0 <= m && m < data.length) {
      int r = getBit(end);
      int mask = Util.ONE_BITS >>> r;
      data[m] &= mask;
      for (int i = 0; i < m; i++) {
        data[i] = 0;
      }
    }
  }

  /**
   * Set the first bits to one.
   * <p> A = 1000111011</p>
   * <p> A.leftSetToOne( 6 ) = 1111111011</p>
   * @param end The number of bits to be set to one
   */
  public void leftSetToOne(int end) {
    int m = getIndex(end);
    if (0 <= m && m < data.length) {
      int r = getBit(end);
      int mask = Util.ONE_BITS << (Util.INTEGER_SIZE - r);
      data[m] |= mask;
      for (int i = 0; i < m; i++) {
        data[i] = Util.ONE_BITS;
      }
    }
  }

  /**
   * Performs the and operator between the bit array and the given bit array.
   * <p>  A = 10011001011</p>
   * <p>  B = 0101010101</p>
   * <p>  A.and( B ) = 00010001011</p>
   * <p>  B.and( A ) = 0001000101</p>
   * @param arg2 The array used to perform the and operator
   */
  public void and(Array arg2) {
    arg2.rightSetToOne(arg2.n);
    int m = arg2.data.length;
    if (data.length < m) { m = data.length; }
    for (int i = 0; i < m; i++) {
      data[i] &= arg2.data[i];
    }
  }

  /**
   * Performs the or operator between the bit array and the given bit array.
   * <p>  A = 10011001011</p>
   * <p>  B = 0101010101</p>
   * <p>  A.or( B ) = 11011101011</p>
   * <p>  B.or( A ) = 1101110101</p>
   * @param arg2 The array used to perform the or operator
   */
  public void or(Array arg2) {
    arg2.rightSetToZero(arg2.n);
    int m = arg2.data.length;
    if (data.length < m) { m = data.length; }
    for (int i = 0; i < m; i++) {
      data[i] |= arg2.data[i];
    }
  }

  /**
   * Performs the xor operator between the bit array and the given bit array.
   * <p>  A = 10011001011</p>
   * <p>  B = 0101010101</p>
   * <p>  A.xor( B ) = 11001100001</p>
   * <p>  B.xor( A ) = 1100110000</p>
   * @param arg2 The array used to perform the xor operator
   */
  public void xor(Array arg2) {
    int m = arg2.data.length;
    if (data.length < m) { m = data.length; }
    for (int i = 0; i < m; i++) {
      data[i] ^= arg2.data[i];
    }
  }

  /**
   * Flips all the bits in the bit array
   * <p>  A = 10001001</p>
   * <p>  A.not() = 01110110</p>
   */
  public void not() {
    for (int i = 0; i < data.length; i++) {
      data[i] ^= Util.ONE_BITS;
    }
  }

  /**
   * Flips the bit given
   * <p> A = 10011011</p>
   * <p> A.not( 3 ) = 10001011</p>
   * <p> A.not( 5 ) = 10011111</p>
   * @param bit apply not
   */
  public void not(int bit) {
    int m = getIndex(bit);
    int p = getBit(bit);
    data[m] ^= (Util.HIGHEST_BIT >>> p);
  }

  /**
   * Converts the bit array to a string
   * <p>  A = 1000111</p>
   * <p>  A.toString() = "1000111"</p>
   * @return The String representation of the bit array
   */
  public String toString() {
    String text = "";
    try{ for (int i = 0; i < n; i++) if (get(i)){ text += "1"; } else{ text += "0"; } }catch(Exception e){}	
    return text;
  }

  /**
   * Compares a BitArray with other Object
   * @param obj The bitarray to be compared with this bitarray
   * @return <i>true</i> if the object is a bitarray with the same information than the bit array
   * <i>false</i> otherwise
   */
  public boolean equals(Object obj) {
    boolean flag = (obj instanceof Array);
    if (flag) {
      Array other = (Array) obj;
      int s = size();
      flag = (other.size() == s);
      if (flag && s > 0) {
        for (int i = 0; i < data.length - 1 && flag; i++) flag = (data[i] == other.data[i]);
        if(flag){
        	try{
        		for (int i = (data.length - 1) * Util.INTEGER_SIZE; i < s && flag; i++)	flag = (get(i) == other.get(i));
        	}catch(Exception e ){}
        }
      }
    }
    return flag;
  }

  // Special methods and atributes
 /**
   * If the BitArray is using Gray code for representing the integer numbers
   */
  public static boolean useGrayCode = false;

 /**
   * Returns the integer at the given buffer position.
   * @param i The buffer position
   * @return The integer at the given buffer position.
   */
  public int getInt (int i) {
    int x = data[i];
    if (useGrayCode) { x = Util.grayToBinary(x); }
    return x;
  }

    /**
   * Sets the integer value at the given buffer position.
   * @param i The buffer position
   * @param value The new integer for the given buffer position.
   */
  public void setInt (int i, int value) {
    if (useGrayCode) {
      data[i] = Util.binaryToGray(value);
    } else {
      data[i] = value;
    }
  }

  /**
   * Gets the array which stores the bits
   * @return The data of the bits array
   */
  public int[] getData() {
	  return data;
  }

  /**
   * Sets the data of the bits array
   * @param data The data of the bits array
   */
  public void setData(int[] data) {
	  this.data = data;
  }

  /**
   * Gets the dimension of the bits array
   * @return The dimension
   */
  public int dimension() {
	  return n;
  }
}
