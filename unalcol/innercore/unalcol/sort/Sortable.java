package unalcol.sort;

import unalcol.services.Service;

public interface Sortable extends Comparable{
    /**
     * Determines if one elements is less, equal or greater than other.
     * A value < 0 indicates that one is less than two, a value = 0 indicates
     * that one is equal to two and a value > 0 indicates that one is greater than two
     * @param one First object to be compared
     * @param two Second object to be compared
     * @return a value < 0 if one < two, 0 if one == two and > 0 if one > two.
     */
	default int compare(Object two){ return Sortable.service(this).compare(this, two); }

	/**
	 * Determines if the object one is less than (in some order) object two
	 * @param one The first object to compare
	 * @param two The second object to compare
	 * @return (one<two)
	 */
	default boolean lt(Object two){ return (compare(two) < 0); }

	/**
	 * Determines if the object one is greater than (in some order) object two
	 * @param one The first object to compare
	 * @param two The second object to compare
	 * @return (one>two)
	 */
	default boolean gt(Object two){ return (compare(two) > 0); }

	/**
	 * Determines if the object one is equal to the object two
	 * @param one The first object to compare
	 * @param two The second object to compare
	 * @return (one==two)
	 */
	default boolean eq(Object two){ return (compare(two) == 0); }

	/**
	 * Determines if the object one is less than or equal to (in some order) object two
	 * @param one The first object to compare
	 * @param two The second object to compare
	 * @return (one<=two)
	 */
	default boolean le(Object two){ return (compare(two) <= 0); }

	/**
	 * Determines if the object one is greater than or equal to (in some order) object two
	 * @param one The first object to compare
	 * @param two The second object to compare
	 * @return (one>=two)
	 */
	default boolean ge(Object two){ return (compare(two) >= 0); }    
    
	default Order service(){ return Sortable.service(this); }
    
    public static Order service( Object caller ){
    	Order obj;
    	try { obj = (Order)Service.provider(Order.class,caller); }
    	catch (Exception e){
			obj = new Order() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public int compare(Object one, Object two){
					if( one==two ) return 0;
					if( one instanceof java.lang.Comparable ) return ((java.lang.Comparable)one).compareTo(two);
					return Integer.MAX_VALUE;
				}
				public String toString(){ return "Order"; }
			};
			Service.register(obj, Object.class);
    	}
    	return obj;
    }
    
    public static Sortable cast( Object obj ){
    	if( obj instanceof Sortable ) return (Sortable)obj;
    	return new Sortable(){ public Order service(){ return Sortable.service(obj); } };
    }
}