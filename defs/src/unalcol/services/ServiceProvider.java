package unalcol.services;

public interface ServiceProvider {
	public Object run( String service, Object caller, Object... args ) throws Exception;
	public String[] provides();
}