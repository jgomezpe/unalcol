package unalcol.llong;

public class Order implements unalcol.sort.Order {

   /**
     * Determines if the first Integer is less than (in some order) the second Integer (one<two)
     * @param one First Integer
     * @param two Second Integer
     * @return (one<two)
     */
    public int compare(Long one, Long two){ return one.compareTo(two); }

	@Override
	public int compare(Object one, Object two){ return compare((Long)one, (Long)two); }
	
	@Override
	public String toString(){ return "LongOrder"; } 	
}