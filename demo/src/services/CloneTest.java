package services;

import unalcol.clone.Clone;
import unalcol.clone.DefaultClone;
import unalcol.clone.ShallowClone;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;

public class CloneTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
		service.register(new DefaultClone(), Object.class);      
		service.register(new ConsoleTracer<Object>(), Object.class);
		Service.set(service);
	}
	
	/**
	 * Obtaining the default Clone service 
	 */
	public static void default_clone() throws Exception{
		System.out.println("############Testing the default clone service############");
		String s = "Hello world!";
		// We can use the Service class method (it is general)
		String cs = (String)Service.run(Clone.name,s);       
		System.out.println("Original: "+s);
		System.out.println("Clone: "+cs);
		// or we can use the specialized method in Clone (it is specific for Clone)
		cs = (String)Service.run(Clone.name, s);
		System.out.println("Clone: "+cs);
		System.out.println("Is it a shallow copy?"+(cs==s));        		
	}
	
	/**
	 * Comparing the shallow vs the wrapper clone services
	 */
	public static void shallow_wrapper() throws Exception{
		System.out.println("############Comparing the Shallow vs the Wrapper (by default) clone services############");
		String s = "Hello World!";
		String cs = (String)Service.run(Clone.name,s);
		Service.run(Tracer.start, s);
		Service.run(Tracer.name, s, "Original:", s);
		Service.run(Tracer.name, cs, "Clone:", cs);
		Service.run(Tracer.name, cs, "Is it a Shallow copy?"+(cs==s));        
		Clone<Object> shallow = new ShallowClone<Object>();
		ServicePool service = (ServicePool)Service.get();
		service.register(shallow, String.class);
		System.out.println("Now we are using shallow copy method.. Careful it will be the clone method from now on");
		cs = (String)Service.run(Clone.name, s);
		service.run(Tracer.name, cs, "Clone:"+cs);
		service.run(Tracer.name, cs, "Is it a shallow copy?"+(cs==s)); 
		Service.run(Tracer.close, Object.class);
	}
	
	/**
	 * Testing the clone service on primitive type arrays 
	 */
	public static void cloneArray() throws Exception{
		System.out.println("############Testing the clone service on arrays############");
		int[] a = new int[]{1,2,3,4};
		int[] b = (int[])Service.run(Clone.name,a);
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