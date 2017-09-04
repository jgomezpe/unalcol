package unalcol.services;

public interface MicroService<T> extends CallerNamePair<T>{
	public Object run( Object... args ) throws Exception;
	public default boolean multiple(){ return false; }
	public String[] provides();
}