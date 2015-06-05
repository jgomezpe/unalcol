package unalcol.sort;

import unalcol.algorithm.Algorithm;

/**
 * <p>Abstract Sorting algorithm for Arrays of objects</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Sort<T> extends Algorithm<T[], T[]>{
    /**
     * Order used for sorting the objects
     */
    protected Order<T> order = null;

    /**
     * Indicates if the sorting algorithm uses the same array for returning the sorted objects
     * or not. A <i>true</i> value indicates that the algorithm should returns the sorted objects
     * in the same array, <i>false</i> if the algorithm should creates a new array.
     */
    protected boolean overwrite = true;

    /**
     * Default constructor
     */
    public Sort(){}

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Sort(Order<T> order) {
        this.order = order;
    }

    /**
     * Creates a sorting algorithm using the given order and overwriting array flag
     * @param order Order used for sorting the objects
     * @param overwrite If the array should be overwritten or not
     */
    public Sort(Order<T> order, boolean overwrite) {
        this.overwrite = overwrite;
        this.order = order;
    }

    /**
     * Sorts the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param order Order used for sorting the objects
     */
    public void apply(T[] a, Order<T> order){
        this.order = order;
        apply( a, 0, a.length );
    }

    /**
     * Sorts the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param order Order used for sorting the objects
     */
    public void apply(T[] a, int start, int end, Order<T> order){
        this.order = order;
        apply( a, start, end );
    }

    /**
     * Sorts a portion of the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     * @return <i>true</i> If the sorting process was done without fails, <i>false</i> otherwise
     */
    public abstract boolean apply(T[] a, int start, int end);

    /**
     * Runs the sorting algorithm on the given array and the established order
     * @param input Array to be sorted
     * @return Sorted array
     */
    @Override
    public T[] apply(T[] input) {
        if (!overwrite) {
            input = input.clone();
        }
        apply(input, 0, input.length);
        return input;
    }

    @SuppressWarnings("unchecked")
	public Order<T> getOrder( T[] a ){
        if( order == null ){
            int k=0;
            while( k<a.length && a[k] == null ){
                k++;
            }
            if( k<a.length ){
               order = (Order<T>)Order.get(a[k]);
            }
        }
        return order;
    }
}