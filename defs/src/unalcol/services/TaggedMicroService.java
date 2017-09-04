package unalcol.services;

import unalcol.types.tag.AbstractTags;

public interface TaggedMicroService extends AbstractTags, MicroService{
	public static final String CALLER="caller";
	public static final String NAME="name";
	public default void setCaller( Object caller ){ set(CALLER, caller); }
	public default Object caller(){ return data(CALLER); }
	public default void setName( String name ){ set(NAME, name); }
	public default String name(){ return (String)data(NAME); }
}