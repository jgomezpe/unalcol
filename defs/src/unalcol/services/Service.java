package unalcol.services;

public class Service {
	// Defining the infra-structure
	private static ServiceProvider pool;
	public static ServiceProvider get(){ return pool; }
	public static void set(ServiceProvider pool){ Service.pool=pool; }		
	public static Object run( String service, Object caller, Object... args ) throws Exception{
		return pool.run(service, caller, args); 
	}
	public static String[] provides(){ return pool.provides(); }
}