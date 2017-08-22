package unalcol.services;

public interface MicroService {
	public Object apply( Object obj, Object... args ) throws Exception;
}