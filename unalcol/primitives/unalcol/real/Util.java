package unalcol.real;

/**
 * <p>Set of constants and methods operating on the primitive double data type,
 * for example, for defining the precision in math operations</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Util {
    /**
     * Precision for considering two double values equivalents
     */
    public static final double PRECISION = 1e-10;

    /**
     * Determines if two double values are equivalents according to the precision. Two values are equivalent if
     * abs(x-y) <= precision
     * @param x First double value to be compared
     * @param y Second double value to be compared
     * @return <i>true</i> if <i>abs(x-y) <= precision</i>, <i>false</i> otherwise
     */
    public static boolean equal(double x, double y) {
        return (Math.abs(x - y) <= PRECISION);
    }

    /**
     * Determines if the given double value is zero (according to the precision) or not
     * @param x double value to be analized
     * @return <i>true</i> if <i>abs(x) <= precision</i>, <i>false</i> otherwise
     */
    public static boolean isZero(double x) {
        return (Math.abs(x) <= PRECISION);
    }
    
	public static double cast( Object x ){
		if( x instanceof Double ) return (Double)x;
		else return (Integer)x;
	}    
}
