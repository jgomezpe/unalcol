package unalcol.integer.array;

import unalcol.integer.Order;

public class Search {
	protected Order order;
	protected int[] sorted;
	
    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     */
	public Search(int[] sorted){ this( sorted, new Order() ); }

    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     * @param order Order used for locating the object
     */
	public Search(int[] sorted, Order order){
		this.order = order;
		this.sorted = sorted;
	}
	
	public void set( int[] sorted ){ this.sorted = sorted; } 

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(int x) { return find( 0, sorted.length, x ); }

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(int start, int end, int x) {
        int pos = findRight(start, end, x);
        if (pos > start && order.compare(x, sorted[pos - 1]) == 0) pos--;
        else pos = -1;
        return pos;
    }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(int start, int end, int x){ return (find(start, end, x) != -1); }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(int x) { return (find(x) != -1); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(int x) { return findRight( 0, sorted.length, x ); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(int start, int end, int x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (order.compare(x, sorted[a]) < 0) return start;
            if (order.compare(x, sorted[b]) >= 0) return end;
            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (order.compare(x, sorted[m]) < 0) b = m;
                else a = m;
            }
            return b;
        }else return start;
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(int x){ return findLeft( 0, sorted.length, x ); }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(int start, int end, int x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (order.compare(x, sorted[a]) <= 0)  return start-1;
            if (order.compare(x, sorted[b]) > 0) return b;
            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (order.compare(x, sorted[m]) <= 0) b = m;
                else a = m;
            }
            return a;
        }else return start;
    }
}
