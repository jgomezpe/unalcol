package unalcol.types.object.dynamic;

import unalcol.types.object.Thing;

public interface DynThing extends DynObj, Thing{
	public static final String ID="id";
	default String id(){ return (String)get(ID); }
	default void setId( String id ){ set(ID,id); }
}