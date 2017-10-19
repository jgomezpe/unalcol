package unalcol.sort;

import unalcol.services.AbstractMicroService;
import unalcol.services.Service;

public interface Comparator<T> extends AbstractMicroService<T>{
	// The MicroService methods
	public static final String name="comparator";
	public static final String eq=name+".eq";
	public static final String ne=name+".ne"; 

	/**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public boolean eq(T one, T two);

    /**
     * Determines if the object one is equal to the object two
     * @param one The first object to compare
     * @param two The second object to compare
     * @return (one==two)
     */
    public default boolean ne(T one, T two){ return !eq(one, two); }
    
	public static final String[] methods = new String[]{eq,ne}; 

	@Override
	public default String[] provides(){ return methods;	}

	@SuppressWarnings("unchecked")
	public default Object run( Object... args ) throws Exception{
		String service=name();
		Object obj=caller(); 
		if(service.equals(eq)) return eq((T)obj,(T)args[0]);
		if(service.equals(ne)) return ne((T)obj,(T)args[0]);
		throw new Exception("Undefined service "+service);
	} 
	
	public static boolean equals( String str1, String str2 ){ return str1.equals(str2); }
	public static boolean equals( Object one, Object two ){
		if( one==two ) return true;
		if( one==null || two==null ) return false;
		if( one instanceof String && two instanceof String ) return equals((String)one, (String)two);
		try{ return (Boolean)Service.run(name, one, two); }catch(Exception e){ return one.equals(two); }
	}
	public static boolean nequals( Object one, Object two ){ return !equals(one,two); }
}