package unalcol.real;

/**
 * <p>Compares to Doubles</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class Order implements unalcol.sort.Order {

    /**
     * Determines if the first Double is less than (in some order) the second Double (one<two)
     * @param one First Double
     * @param two Second Double
     * @return (one<two)
     */
    public int compare(Double one, Double two){ return one.compareTo(two); }

	@Override
	public int compare(Object one, Object two){ return compare((Double)one, (Double)two); }
	
	@Override
	public String toString(){ return "DoubleOrder"; }
}