/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package random;

import unalcol.random.real.SimplestGeneralizedPowerLawGenerator;
import unalcol.random.real.StandardGaussianGenerator;
import unalcol.random.real.SymmetricGenerator;

/**
 *
 * @author jgomez
 */
public class DoubleGeneratorDemo {
    
  public static void gaussian(int n){
      System.out.println( "Gaussian" );
//      GaussianGenerator g = new GaussianGenerator(0.0, 1.0);
      StandardGaussianGenerator g = new StandardGaussianGenerator();
      for( int i=0; i<n; i++ ){
          System.out.println( g.generate() );
      }
  }  
  
  public static void power_law(int n){
      System.out.println( "Power Law" );
      SimplestGeneralizedPowerLawGenerator g = new SimplestGeneralizedPowerLawGenerator();
      double[] x = g.generate(n);
      for( int i=0; i<n; i++ ){
          System.out.println( x[i] );
      }
  }
    
  public static void symmetric_power_law(int n){
      System.out.println( "Symmetric Power Law" );
      SymmetricGenerator g = new SymmetricGenerator( new SimplestGeneralizedPowerLawGenerator() );
      double[] x = g.generate(n);
      for( int i=0; i<n; i++ ){
          System.out.println( x[i] );
      }
  }
      
  public static void main( String[] args ){
//      RawGenerator.init();
      int n = 10;
      gaussian(n);
      power_law(n);
      symmetric_power_law(n);
  }    
}
