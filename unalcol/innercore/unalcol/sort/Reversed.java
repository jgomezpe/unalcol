package unalcol.sort;

/**
 * <p>Reverses a given order</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class Reversed implements Order {
    /**
     * Order to be reversed
     */
    protected Order original_order = null;

    /**
     * Creates a reversed order for the given order
     * @param _original_order Order to be reversed
     */
    public Reversed(Order _original_order){ original_order = _original_order; }

    /**
     * Determines if object one is less than (in the reversed order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one<two) <-> (two<one in the original_order)
     */
    @Override
    public int compare(Object one, Object two){ return original_order.compare(two, one); }
    
    public String toString(){ return "reversed-order"; }
}