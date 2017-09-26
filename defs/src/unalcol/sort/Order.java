package unalcol.sort;

import unalcol.services.AbstractMicroService;

/**
 * <p>Abstract class, determines if the object one is less, greater or equal than object two</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public interface Order<T> extends AbstractMicroService<T>{
    /**
     * Determines if one elements is less, equal or greater than other.
     * A value < 0 indicates that one is less than two, a value = 0 indicates
     * that one is equal to two and a value > 0 indicates that one is greater than two
     * @param one First object to be compared
     * @param two Second object to be compared
     * @return a value < 0 if one < two, 0 if one == two and > 0 if one > two.
     */
    public int compare(T one, T two);

    /**
     * Determines if the object one is less than (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one<two)
     */
    public default boolean lt(T one, T two){ return (compare(one, two) < 0); }

    /**
     * Determines if the object one is greater than (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one>two)
     */
    public default boolean gt(T one, T two){ return (compare(one, two) > 0); }

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public default boolean eq(T one, T two){ return (compare(one, two) == 0); }

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public default boolean ne(T one, T two){ return (compare(one, two) != 0); }

    /**
     * Determines if the object one is less than or equal to (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one<=two)
     */
    public default boolean le(T one, T two){ return (compare(one, two) <= 0); }

    /**
     * Determines if the object one is greater than or equal to (in some order) object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one>=two)
     */
    public default boolean ge(T one, T two){ return (compare(one, two) >= 0); }    
    
	// The MicroService methods
	public static final String name="order";
	public static final String compare=name+".compare"; 
	public static final String lt=name+".lt"; 
	public static final String gt=name+".gt"; 
	public static final String eq=name+".eq"; 
	public static final String ne=name+".ne"; 
	public static final String le=name+".le"; 
	public static final String ge=name+".ge"; 
	
	public static final String[] methods = new String[]{name,compare,lt,ge,eq,ne,le,ge}; 

	@Override
	public default String[] provides(){ return methods;	}

	@SuppressWarnings("unchecked")
	public default Object run( Object... args ) throws Exception{
		String service=name();
		Object obj=caller(); 
		if(service.equals(name)||service.equals(compare)) return compare((T)obj,(T)args[0]);
		if(service.equals(lt)) return lt((T)obj,(T)args[0]);
		if(service.equals(gt)) return gt((T)obj,(T)args[0]);
		if(service.equals(eq)) return eq((T)obj,(T)args[0]);
		if(service.equals(ne)) return ne((T)obj,(T)args[0]);
		if(service.equals(le)) return le((T)obj,(T)args[0]);
		if(service.equals(ge)) return ge((T)obj,(T)args[0]);
		throw new Exception("Undefined service "+service);
	}
}