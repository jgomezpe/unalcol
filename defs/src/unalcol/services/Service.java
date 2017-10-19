package unalcol.services;

import unalcol.types.collection.Collection;

public class Service {
	public static final String USES="uses.";
	// Defining the infra-structure
	private static ServiceProvider pool=null;
	public static ServiceProvider get(){ return pool; }
	public static void set(ServiceProvider pool){ Service.pool=pool; }		
	public static Object run( String service, Object caller, Object... args ) throws Exception{ return pool.run(service, caller, args); }
	public static Collection<String> provides(){ return pool.provides(); }
}