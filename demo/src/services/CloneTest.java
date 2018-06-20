package services;

import unalcol.clone.Cloneable;
import unalcol.clone.Clone;
import unalcol.clone.ShallowClone;
import unalcol.services.Service;
import unalcol.services.ProvidersSet;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Traceable;

public class CloneTest {
	public static void init_services(){
		Service.register(new ConsoleTracer(), Object.class );
	}
	
	/**
	 * Obtaining the default Clone service 
	 */
	public static void default_clone() throws Exception{
		System.out.println("############Testing the default clone service############");
		String s = "Hello world!";
		// We can use the Service class method (it is general)
		Cloneable c = Cloneable.cast(s);
		String cs = (String)c.clone();       
		System.out.println("Original: "+s);
		System.out.println("Clone: "+cs);
		// or we can use the specialized method in Clone (it is specific for Clone)
		System.out.println("Is it a shallow copy?"+(cs==s));        		
	}
	
	/**
	 * Comparing the shallow vs the wrapper clone services
	 */
	public static void shallow_wrapper() throws Exception{
		System.out.println("############Comparing the Shallow vs by default clone services############");
		int[] a = new int[]{1,2,3,4};
		Cloneable ca = Cloneable.cast(a);
		int[] b = (int[])ca.clone();
		Traceable ta = Traceable.cast(a);
		Traceable tb = Traceable.cast(b);
		ta.start_trace();
		ta.trace("Original:", a);
		tb.trace("Clone:", b);
		ta.trace("Is it a Shallow copy?"+(a==b));        
		Clone shallow = new ShallowClone();
		Service.register(shallow, Object.class);
		ProvidersSet set = Service.providers(Clone.class, Object.class);
		for( String opt:set.options() ) System.out.println(opt);
		set.current(shallow.toString());
		System.out.println("Now we are using "+ set.current_id()+" copy method.. Careful it will be the clone method from now on");
		//ca = Cloneable.cast(a);
		b = (int[])ca.clone();
		ta.trace("Original:", a);
		tb = Traceable.cast(b);
		tb.trace("Clone:", b);
		ta.trace("Is it a Shallow copy?"+(a==b));        
		ta.close_trace();
	}
	
	/**
	 * Testing the clone service on primitive type arrays 
	 */
	public static void cloneArray() throws Exception{
		System.out.println("############Testing the clone service on arrays############");
		int[] a = new int[]{1,2,3,4};
		Clone c = Cloneable.service(a);
		int[] b = (int[])c.clone(a);
		System.out.print("Original:");
		for(int i=0; i<a.length; i++ ){
			System.out.print(" "+a[i]);
		}
		System.out.println();
		System.out.print("Clone:");
		for(int i=0; i<a.length; i++ ){
			System.out.print(" "+b[i]);
		}
		System.out.println();
		System.out.println("Is it a shallow copy?"+(a==b));        		
	}
	
	/**
	 * Test the clone service infrastructure
	 * @param args Unused
	 */
	public static void main( String[] args ){
		try{
			init_services(); // Initializing the set of services used here
			default_clone(); // Testing the default clone service
			cloneArray(); // Testing the clone service on primitive type arrays
			shallow_wrapper(); // Testing the shallow vs the wrapper
		}catch(Exception e){ e.printStackTrace(); }
	}
}