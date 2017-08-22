package unalcol.services2;

import java.util.Hashtable;

public class Service{
	protected String name;
	public Service(String name){ this.name=name; }
	
	protected Hashtable<Object,MicroService> micro = new Hashtable<Object,MicroService>();
	
	public void register(Object owner, MicroService micro){ this.micro.put(owner, micro);	}
	
	public String name(){ return name; }
	
	protected Object apply( Class<?> owner, Object obj, Object... args ) throws Exception{
		MicroService m = micro.get(owner);
		if(m!=null) return m.apply(owner, args);
		else{
			try{ return apply( owner.getSuperclass(), obj, args ); }catch(Exception e ){}
			Class<?>[] superTypes = owner.getInterfaces();
			for( int i=0; i<superTypes.length; i++ )
				try{ return apply( superTypes[i], obj, args ); }catch(Exception e){}
		}
		throw new Exception("Service "+name()+" not available for object "+obj);
	}
	
	public Object apply( Object obj, Object... args ) throws Exception{
		MicroService m = micro.get(obj);
		if(m!=null) return m.apply(obj, args);
		else return apply(obj.getClass(),obj,args);
	}

	protected static Hashtable<String, Service> services = new Hashtable<String,Service>();
	
	public static Object run( String service, Object owner, Object... args ) throws Exception{
		Service s = services.get(service);
		if( s!= null ) return s.apply(owner, args);
		else throw new Exception("Undefined service "+service);
	}
	
	public static void register(String service, Object owner, MicroService micro){
		Service s = services.get(service);
		if( s!= null ){
			s = new Service(service);
			services.put(service, s);
		}
		s.register(owner, micro);
	}
}