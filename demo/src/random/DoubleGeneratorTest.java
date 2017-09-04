/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package random;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestGeneralizedPowerLawGenerator;
import unalcol.random.real.StandardGaussianGenerator;
import unalcol.random.real.SymmetricGenerator;
import unalcol.random.real.UniformGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;

/**
 *
 * @author jgomez
 */
public class DoubleGeneratorTest{
	public static void init_services(){
		ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);         
//        service.register(new ConsoleTracer(), Object.class);
        Service.set(service);
	}
	
	public static DoubleGenerator uniform(){
		System.out.println( "Uniform" );
		return  new UniformGenerator(0.0,2.0);
	}  
  
	public static DoubleGenerator gaussian(){
		System.out.println( "Gaussian" );
		return  new StandardGaussianGenerator();
	}  
  
	public static DoubleGenerator power_law(){
		System.out.println( "Power Law" );
		return new SimplestGeneralizedPowerLawGenerator();
	}
    
	public static DoubleGenerator symmetric_power_law(){
	    System.out.println( "Symmetric Power Law" );
	    return new SymmetricGenerator( new SimplestGeneralizedPowerLawGenerator() );
	}
      
	public static void main( String[] args ){
		init_services();
		// DoubleGenerator g = uniform();
		DoubleGenerator g = gaussian();
		// DoubleGenerator g = power_law();
		// DoubleGenerator g = symmetric_power_law();
		int n = 10;
		// Generating an array of ten random values
		double[] x = g.generate(n);
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