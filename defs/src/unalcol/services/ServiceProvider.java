package unalcol.services;

import unalcol.types.collection.Collection;

public interface ServiceProvider {
	public Object run( String service, Object caller, Object... args ) throws Exception;
	public Collection<String> provides();
}