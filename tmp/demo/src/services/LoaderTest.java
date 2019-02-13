package services;

import unalcol.reflect.Loader;

public class LoaderTest {
	public static void main( String[] args ){
		// Defines the base class of the array
		Class<?> ocl = String.class;
		// Creates the class name of a 3D array of the base class
		String clNm = "[[[L" + ocl.getName() + ";";
		try {
			// Load the class of an 3D array of the base class
			Loader loader = new Loader();
			Class<?> cl = loader.loadClass(clNm);
			// Prints the class name of the 3D array of the base class
			System.out.println( cl.getName() );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
