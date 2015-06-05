package unalcol.types.real;

import unalcol.sort.*;


/**
 * <p>Compares to Doubles</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class DoubleOrder extends Order<Double> {

    /**
     * Determines if the first Double is less than (in some order) the second Double (one<two)
     * @param one First Double
     * @param two Second Double
     * @return (one<two)
     */
    @Override
    public int compare(Double one, Double two) {
        return one.compareTo(two);
    }
}
