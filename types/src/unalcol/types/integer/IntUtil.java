package unalcol.types.integer;


/**
 * <p>Set of constants and methods operating on the primitive <i>int</i> data type,
 * for example, for accessing the bit representation of an int </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */


public class IntUtil {

    /**
     * The minimum integer that can be represented
     */
    public static int MIN_INT;

    /**
     * The maximum integer that can be represented
     */
    public static int MAX_INT;

    /**
     * A integer with the lowest bit set in one and the remaining bits set in zero
     */
    public static int LOWEST_BIT = 1;

    /**
     * A integer with the highest bit set in one and the remaining bits set in zero
     */
    public static int HIGHEST_BIT = 1 << 31;

    /**
     * The number of bits used to represent an integer
     */
    public static int INTEGER_SIZE = 32;

    /**
     * Integer used to apply the modulus operation respect to the integer size
     * <p>  x & MOD_MASK = x mod INTEGER_SIZE </p>
     */
    public static int MOD_MASK = 31;

    /**
     * Integer used to apply the division operation respect to the integer size
     * <p>  x >>> DIV_MASK = x div INTEGER_SIZE </p>
     */
    public static int DIV_MASK = 5;

    /**
     * A integer with all the bits set in one
     */
    public static int ONE_BITS = -1;

    /**
     * Initializes the bit array static information (INTEGER_SIZE in bits, MOD_MASK, etc)
     */
    static {
        LOWEST_BIT = 1;
        ONE_BITS = -1;
        INTEGER_SIZE = 0;
        int val = 1;
        while (val != 0) {
            val <<= 1;
            INTEGER_SIZE++;
        }

        MOD_MASK = (INTEGER_SIZE - 1);

        MIN_INT = (1 << MOD_MASK);
        MAX_INT = MIN_INT - 1;

        HIGHEST_BIT = 1 << MOD_MASK;
        DIV_MASK = -1;
        val = INTEGER_SIZE;
        while (val > 0) {
            val >>>= 1;
            DIV_MASK++;
        }
    }

    /**
     * Transforms an unsigned binary number to reflected binary Gray code. For example the integer value
     * 2 is represented in binary code as: 0000.....0010
     * 2 is represented in gray code as: 0000.....0011 (3 in binary)
     * binarytoGray( 2 ) = 3
     * @param num Integer number to be converted to Gray code
     * @return Integer value that represents the value num (in gray binary format)
     */
    public static int binaryToGray(int num) {
        return (num >>> 1) ^ num;
    }

    /**
     * Determines the minimum number of bits required for representing n different values
     * @param n Number of different values
     * @return Number of bits required for representing such n different values
     */
    public static int getBitsNumber(int n) {
        return (int) (Math.log(n) / Math.log(2.0) + 0.9999);
    }

    /**
     * Transforms a reflected binary Gray code number to a unsigned binary number. For example,
     * 0000.....0011 in binary code represents 3, but in Gray code represents 2.
     * grayToBinary( 3 ) = 2
     * @param num Integer number in Gray code that will be converted to Binary code
     * @return Unsigned Integer value that is represented by the Gray value num
     */
    public static int grayToBinary(int num) {
        int k = (INTEGER_SIZE >>> 1);
        int temp = num;
        while (k > 0) {
            temp ^= (temp >>> k);
            k >>>= 1;
        }
        return temp;
    }
}
