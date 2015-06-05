package unalcol.sort;

import unalcol.service.ServiceCore;


/**
 * <p>Abstract class, determines if the object one is less, greater or equal than object two</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public abstract class Order<T>{
    /**
     * Determines if one elements is less, equal or greater than other.
     * A value < 0 indicates that one is less than two, a value = 0 indicates
     * that one is equal to two and a value > 0 indicates that one is greater than two
     * @param one First object to be compared
     * @param two Second object to be compared
     * @return a value < 0 if one < two, 0 if one == two and > 0 if one > two.
     */
    public abstract int compare(T one, T two);
    
    /**
     * Gets the order used by the given object
     * @param owner Owner of the order to be retrieved
     * @return Currently used order method (by the object)
     */
    public static Order<?> get( Object owner ){
    	if( ServiceCore.get(Object.class, Order.class) == null ) ServiceCore.set(Object.class, new OrderWrapper());
        return ((Order<?>)ServiceCore.get(owner, Order.class));
    }
    
    /**
     * Gets the order used by the given object
     * @param owner Owner of the order to be retrieved
     * @return Currently used order method (by the object)
     */
    public static boolean set( Object owner, Order<?> order ){
        return ServiceCore.set(owner, Order.class, order);
    }
    
    /**
     * Determines if the object one is less than (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one<two)
     */
    public static boolean lessThan(Object one, Object two) {
        @SuppressWarnings("unchecked")
		Order<Object> order = (Order<Object>)get(one);
        return (order.compare(one, two) < 0);
    }

    /**
     * Determines if the object one is greater than (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one>two)
     */
    public static boolean greaterThan(Object one, Object two) {
        @SuppressWarnings("unchecked")
        Order<Object> order = (Order<Object>)get(one);
        return (order.compare(one, two) > 0);
    }

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public static boolean equalTo(Object one, Object two) {
        @SuppressWarnings("unchecked")
        Order<Object> order = (Order<Object>)get(one);
        return (order.compare(one, two) == 0);
    }

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public static boolean differentTo(Object one, Object two) {
        @SuppressWarnings("unchecked")
        Order<Object> order = (Order<Object>)get(one);
        return (order.compare(one, two) != 0);
    }

    /**
     * Determines if the object one is less than or equal to (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one<=two)
     */
    public static boolean lessThanEqualTo(Object one, Object two) {
        @SuppressWarnings("unchecked")
        Order<Object> order = (Order<Object>)get(one);
        return (order.compare(one, two) <= 0);
    }

    /**
     * Determines if the object one is greater than or equal to (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one>=two)
     */
    public static boolean greaterThanEqualTo(Object one, Object two) {
        @SuppressWarnings("unchecked")
        Order<Object> order = (Order<Object>)get(one);
        return (order.compare(one, two) >= 0);
    }

    /**
     * Determines if one elements is less, equal or greater than other.
     * A value < 0 indicates that one is less than two, a value = 0 indicates
     * that one is equal to two and a value > 0 indicates that one is greater than two
     * @param one First object to be compared
     * @param two Second object to be compared
     * @return a value < 0 if one < two, 0 if one == two and > 0 if one > two.
     */
    public static int comp(Object one, Object two) {
        @SuppressWarnings("unchecked")
        Order<Object> order = (Order<Object>)get(one);
        return order.compare(one, two);
    }
    
}