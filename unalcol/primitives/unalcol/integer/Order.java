package unalcol.integer;

/**
 * <p>Compares two Integer</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
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
    public int compare(int one, int two){ return (one-two); }

    /**
     * Determines if the first Integer is less than (in some order) the second Integer (one<two)
     * @param one First Integer
     * @param two Second Integer
     * @return (one<two)
     */
    public int compare(Integer one, Integer two){ return one.compareTo(two); }

	@Override
	public int compare(Object one, Object two) { return compare((Integer)one, (Integer)two); }
	
	@Override
	public String toString(){ return "IntegerOrder"; }	
}