package unalcol.sort;

public interface Comparator{
	/**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public boolean eq(Object one, Object two);
}