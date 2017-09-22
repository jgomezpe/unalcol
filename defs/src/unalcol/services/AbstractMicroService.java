package unalcol.services;

import unalcol.AbstractThing;

public interface AbstractMicroService<T> extends AbstractThing{
	public static final String CALLER="caller";
	public static final String NAME="name";
	
	public default void setCaller( T caller ){ set(CALLER, caller); }
	@SuppressWarnings("unchecked")
	public default T caller(){ return (T)get(CALLER); }
	public default void setName( String name ){ set(NAME, name); }
	public default String name(){ return (String)get(NAME); }
	public default boolean multiple(){ return false; }

	public default AbstractMicroService<?> wrap(String id){ return null; }
	public default void setMicroService( String id, AbstractMicroService<?> clone ){ set(id, clone); }
	public default AbstractMicroService<?> getMicroService( String id ){
		AbstractMicroService<?> service = (AbstractMicroService<?>)get(id);
		if(service==null){
			service = wrap(id);
			if( service != null ) set(id,service);
		}
		return service;
	}
	
	public Object run( Object... args ) throws Exception;
	public String[] provides();
}