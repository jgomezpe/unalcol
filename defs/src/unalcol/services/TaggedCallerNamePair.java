package unalcol.services;

import unalcol.types.tag.AbstractTags;

public interface TaggedCallerNamePair<T> extends AbstractTags, CallerNamePair<T>{
	public static final String CALLER="caller";
	public static final String NAME="name";
	public default void setCaller( T caller ){ set(CALLER, caller); }
	@SuppressWarnings("unchecked")
	public default T caller(){ return (T)data(CALLER); }
	public default void setName( String name ){ set(NAME, name); }
	public default String name(){ return (String)data(NAME); }
}