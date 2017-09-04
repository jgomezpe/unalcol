package unalcol.services;

public interface CallerNamePair<T> {
	public T caller();
	public void setCaller( T caller );
	public String name();
	public void setName( String name );
}