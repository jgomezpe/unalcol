package unalcol.service;

public interface Service{ 
	public String[] provides();
	public Object apply( Object owner, Object... args );
        
	public static Object run( Object owner, String service, Object... args ){
		Class<?> stype = ServiceCore.get(service);
		Service s = (Service)ServiceCore.get(owner, stype); // @TODO: Next version must change all the service infra-structure using Services
		return s.apply(owner, args);
	}
}