package services;

import unalcol.clone.DefaultClone;
import unalcol.instance.DefaultInstance;
import unalcol.instance.Instance;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.ConsoleTracer;

public class InstanceTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
		service.register(new DefaultInstance<Object>(), Object.class);
		service.register(new DefaultClone(), Object.class);         
		service.register(new ConsoleTracer<Object>(), Object.class);
		Service.set(service);
	}
	
	public static void main( String[] args ){
		try{
			init_services();
			DemoC x = (DemoC)Service.run(Instance.name, DemoC.class, new DemoA());
			System.out.println( x );
			x = (DemoC)Service.run(Instance.name, DemoC.class, new DemoB());
			System.out.println( x );
		}catch(Exception e){ e.printStackTrace(); }
	}

}
