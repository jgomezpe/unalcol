package unalcol.vc;

import unalcol.types.collection.Collection;

public interface VCEnd<T,S> {
	public T get( String id );
	public Collection<String> list();
	default public void load( String id ){}
	public VCEnd<S,T> get();
	default void set( VCEnd<S,T> complement ){ if( complement.get() != this ) complement.set(this); }
	default boolean link(S complement){ return false; }
	default boolean register(T component){ return false; }
}