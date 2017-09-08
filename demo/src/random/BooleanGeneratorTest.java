package random;

import unalcol.clone.DefaultClone;
import unalcol.random.raw.JavaGenerator;
import unalcol.random.util.RandBool;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.ConsoleTracer;

public class BooleanGeneratorTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
		service.register(new DefaultClone(), Object.class);         
		service.register(new ConsoleTracer<Object>(), Object.class);
	    service.register(new JavaGenerator(), Object.class);         
		Service.set(service);
	}
	
	public static void main( String[] args ){
		init_services();
		// false is generated with probability 0.7
		// true is generated with probability 0.3
		RandBool g = new RandBool(0.7);
		int n = 10;
		// Generating an array of ten random values
		boolean[] x = g.generate(n);
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}
		System.out.println("****************");
		// Generating ten random values
		for( int i=0; i<n; i++ ){
			System.out.println(g.next());
		}		
	}
}