package unalcol.types.collection.vector;


import unalcol.sort.*;

/**
 * <p>Title: SortedVectorSearch</p>
 * <p>Description: Searching algorithms for sorted vectors</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SortedVectorSearch<T> {
    Search<T> search = new Search<T>();

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return The position of the given object, -1 if the given object is not in the vector
     */
    public int find(ImmutableVector<T> sorted, T x, Order<T> order) {
        return search.find(sorted.buffer, 0, sorted.size, x, order);
    }

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return The position of the given object, -1 if the given object is not in the vector
     */
    public int find(ImmutableVector<T> sorted, int start, int end, T x, Order<T> order) {
        return search.find(sorted.buffer, start, (end<=sorted.size)?end:sorted.size, x, order);
    }

    /**
     * Determines if the sorted vector contains the given element (according to the associated order)
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return <i>true</i> if the element belongs to the sorted vector, <i>false</i> otherwise
     */
    public boolean contains(ImmutableVector<T> sorted, T x, Order<T> order) {
        return (find(sorted, 0, sorted.size, x, order) != -1);
    }

    /**
     * Determines if the sorted vector contains the given element (according to the associated order)
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return <i>true</i> if the element belongs to the sorted vector, <i>false</i> otherwise
     */
    public boolean contains(ImmutableVector<T> sorted, int start, int end, T x, Order<T> order) {
        return (find(sorted, start, (end<=sorted.size)?end:sorted.size, x, order) != -1);
    }


    /**
     * Searches for the position of the first element in the vector that is bigger
     * than the given element. The vector should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order verify to order
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(ImmutableVector<T> sorted, T x, Order<T> order) {
        return search.findRight(sorted.buffer, 0, sorted.size, x, order);
    }


    /**
     * Searches for the position of the first element in the vector that is bigger
     * than the given element. The vector should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order verify to order
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(ImmutableVector<T> sorted, int start, int end, T x, Order<T> order) {
        return search.findRight(sorted.buffer, start, (end<=sorted.size)?end:sorted.size, x, order);
    }

    /**
     * Searches for the position of the last element in the vector that is smaller
     * than the element given. The vector should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the element
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(ImmutableVector<T> sorted, T x, Order<T> order) {
        return search.findLeft(sorted.buffer, 0, sorted.size, x, order);
    }

    /**
     * Searches for the position of the last element in the vector that is smaller
     * than the element given. The vector should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the element
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(ImmutableVector<T> sorted, T x, int start, int end, Order<T> order) {
        return search.findLeft(sorted.buffer, start, (end<=sorted.size)?end:sorted.size, x, order);
    }
}
