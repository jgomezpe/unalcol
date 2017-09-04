package unalcol.services;

public interface MicroService{
	public Object caller();
	public void setCaller( Object caller );
	public String name();
	public void setName( String name );
	public Object run( Object... args ) throws Exception;

	public default String[] provides(){ return new String[]{name()}; }
}