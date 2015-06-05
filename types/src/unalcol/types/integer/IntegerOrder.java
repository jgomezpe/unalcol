package unalcol.types.integer;
import unalcol.sort.*;



/**
 * <p>Compares two Integer</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class IntegerOrder extends Order<Integer> {

    /**
     * Determines if the first Integer is less than (in some order) the second Integer (one<two)
     * @param one First Integer
     * @param two Second Integer
     * @return (one<two)
     */
    public int compare(Integer one, Integer two) {
        return one.compareTo(two);
    }
}