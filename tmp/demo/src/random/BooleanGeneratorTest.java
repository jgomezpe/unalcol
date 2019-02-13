package random;

import unalcol.random.util.RandBool;
import unalcol.services.Service;
import unalcol.tracer.ConsoleTracer;

public class BooleanGeneratorTest {
	public static void init_services(){
		Service.register(new ConsoleTracer(), Object.class);
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