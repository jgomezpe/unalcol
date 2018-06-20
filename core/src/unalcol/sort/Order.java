package unalcol.sort;

/**
 * <p>Abstract class, determines if the object one is less, greater or equal than object two</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public interface Order extends Comparator{
    /**
     * Determines if one elements is less, equal or greater than other.
     * A value < 0 indicates that one is less than two, a value = 0 indicates
     * that one is equal to two and a value > 0 indicates that one is greater than two
     * @param one First object to be compared
     * @param two Second object to be compared
     * @return a value < 0 if one < two, 0 if one == two and > 0 if one > two.
     */
    public int compare(Object one, Object two);
    
	/**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    default boolean eq(Object one, Object two){ return compare(one, two)==0; }
}