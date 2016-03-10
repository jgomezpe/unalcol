package services;

import unalcol.clone.Clone;
import unalcol.clone.ShallowClone;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;

public class CloneTest {
	/**
	 * Obtaining the default Clone service 
	 */
	public static void default_clone(){
		System.out.println("############Testing the default clone service############");
		// Obtaining the Clone service for the String class. Since no service has been
		// associated to the String class, it will used the CloneWrapper service (default)
		Clone<?> clone = Clone.get(String.class);
		System.out.println("Clone service for String class"+clone.getClass().getName());
        String s = "Hello world!";
        String cs = (String)Clone.create(s);       
		System.out.println("Original: "+s);
		System.out.println("Clone: "+s);
        System.out.println("Is it a shallow copy?"+(cs==s));        		
	}
	
	/**
	 * Comparing the shallow vs the wrapper clone services
	 */
	public static void shallow_wrapper(){
		System.out.println("############Comparing the Shallow vs the Wrapper (by default) clone services############");
        String s = "Hello World!";
        String cs = (String)Clone.create(s);       
        ConsoleTracer tracer = new ConsoleTracer();
        Tracer.addTracer(String.class, tracer);
        Tracer.trace(s, "Original:",s);
        Tracer.trace(cs, "Clone:",cs);
        Tracer.trace(cs, "Is it a Shallow copy?"+(cs==s));        
        Clone<Object> shallow = new ShallowClone();
        Clone.set(String.class, shallow);
        cs = (String)Clone.create(s);
        Tracer.trace(cs, "Clone:"+cs);
        Tracer.trace(cs, "Is it a shallow copy?"+(cs==s));        		
	}
	
	/**
	 * Testing the clone service on primitive type arrays 
	 */
	public static void cloneArray(){
		System.out.println("############Testing the clone service on arrays############");
		int[] a = new int[]{1,2,3,4};
		int[] b = (int[])Clone.create(a);
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
    	//default_clone(); // Testing the default clone service
    	//shallow_wrapper(); // Testing the shallow vs the wrapper
    	cloneArray(); // Testing the clone service on primitive type arrays 
    }
    
}
