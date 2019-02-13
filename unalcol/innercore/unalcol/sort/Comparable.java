package unalcol.sort;

import unalcol.services.Service;

public interface Comparable {
	/**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    default boolean eq(Object two){ return service().eq(this, two); }

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    default boolean ne(Object two){ return !eq(two); }     
    
    default Comparator service(){ return Comparable.service(this); }
    
    public static Comparator service(Object caller){
    	Comparator obj;
    	try { obj = (Comparator)Service.provider(Comparator.class,caller); }
    	catch (Exception e){
			obj = new Comparator() {
				@Override
				public boolean eq(Object one, Object two) {
					if( one==two ) return true;
					if( one==null || two==null ) return false;
					return one.equals(two);
				}
		
				public String toString(){ return "Comparator"; }
			};
			Service.register(obj, Object.class);
		}
    	return obj;
    }
    
    public static Comparable cast( Object obj ){
    	if( obj instanceof Cloneable ) return (Comparable)obj;
    	return new Comparable(){ public Comparator service(){ return Comparable.service(obj); } };
    }
}