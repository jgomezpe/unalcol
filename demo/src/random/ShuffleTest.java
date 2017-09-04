package random;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.util.Shuffle;
import unalcol.services.Service;
import unalcol.services.ServicePool;

public class ShuffleTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);         
//        service.register(new ConsoleTracer(), Object.class);
        Service.set(service);
	}
	
	public static void int_array(){
		int[] x = new int[100];
		for( int i=0; i<x.length; i++) x[i] = i;
		@SuppressWarnings("rawtypes")
		Shuffle s = new Shuffle();
		s.apply(x);
		for( int i=0; i<x.length; i++) System.out.println(x[i]);		
	}
	
	public static void string_array(){
		String[] x = new String[]{"one", "two", "three", "four", "five"};
		Shuffle<String> s = new Shuffle<String>();
		s.apply(x);
		for( int i=0; i<x.length; i++) System.out.println(x[i]);		
	}
	
	public static void main( String[] args ){
		init_services();
		// int_array();  // Shuffling an int array
		string_array();  //shuffling an String array
	}
}