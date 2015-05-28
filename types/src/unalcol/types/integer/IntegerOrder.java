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
     * Returns the Class that owns the PlugIn
     * @return Class The PlugIns owner class
     */
    public Object owner() {
        return Integer.class;
    }  

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