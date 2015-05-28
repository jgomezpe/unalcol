package unalcol.sort;

/**
 * <p>Searching algorithm for sorted arrays of objects</p>
 * 
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Search<T> {

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param sorted Array of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(T[] sorted, T x, Order<T> order) {
       return find( sorted, 0, sorted.length, x, order );
    }

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param sorted Array of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(T[] sorted, int start, int end, T x, Order<T> order) {
        int pos = findRight(sorted, start, end, x, order);
        if (pos > start && order.compare(x, sorted[pos - 1]) == 0) {
            pos--;
        } else {
            pos = -1;
        }
        return pos;
    }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param sorted Array of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(T[] sorted, int start, int end, T x, Order<T> order) {
        return (find(sorted, start, end, x, order) != -1);
    }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param sorted Array of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(T[] sorted, T x, Order<T> order) {
        return (find(sorted, x, order) != -1);
    }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(T[] sorted, T x, Order<T> order) {
        return findRight( sorted, 0, sorted.length, x, order );
    }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the object
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(T[] sorted, int start, int end, T x, Order<T> order) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (order.compare(x, sorted[a]) < 0) {
                return start;
            }
            if (order.compare(x, sorted[b]) >= 0) {
                return end;
            }

            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (order.compare(x, sorted[m]) < 0) {
                    b = m;
                } else {
                    a = m;
                }
            }
            return b;
        } else {
            return start;
        }
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the element
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(T[] sorted, T x, Order<T> order) {
        return findLeft( sorted, 0, sorted.length, x, order );
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param sorted Vector of elements (should be sorted)
     * @param x Element to be located
     * @param order Order used for locating the element
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(T[] sorted, int start, int end, T x, Order<T> order) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (order.compare(x, sorted[a]) <= 0) {
                return start-1;
            }
            if (order.compare(x, sorted[b]) > 0) {
                return b;
            }

            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (order.compare(x, sorted[m]) <= 0) {
                    b = m;
                } else {
                    a = m;
                }
            }
            return a;
        } else {
            return start;
        }
    }
}
