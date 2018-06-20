package services;

import unalcol.instance.Instanteable;
import unalcol.services.Service;
import unalcol.tracer.ConsoleTracer;

public class InstanceTest {
	public static void init_services(){
		Service.register(new ConsoleTracer(), Object.class );
	}
	
	public static void main( String[] args ){
		try{
			init_services();
			Instanteable ins = Instanteable.cast(DemoC.class);
			DemoC x = (DemoC)ins.create(new DemoA());
			System.out.println( x.getClass() + ":" + x );
			x = (DemoC)ins.create(new DemoB());
			System.out.println( x.getClass() + ":" + x );
		}catch(Exception e){ e.printStackTrace(); }
	}

}
