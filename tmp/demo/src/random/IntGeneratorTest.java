package random;

import unalcol.random.integer.IntRoulette;
import unalcol.random.integer.IntUniform;
import unalcol.random.integer.RandInt;

public class IntGeneratorTest {
	public static RandInt roulette(){
		System.out.println("Roulette");
		// 0  is generated with probability 0.4
		// 1 is generated with probability 0.3
		// 2 is generated with probability 0.2
		// 3 is generated with probability 0.1
		return new IntRoulette(new double[]{0.4,0.3,0.2,0.1});
	} 
	
	public static RandInt uniform(){
		System.out.println("Uniform");
		// Integer numbers between 0 and 99 are generated with the same probability
		return new IntUniform(100);
	}
	
	public static void main( String[] args ){
		RandInt g = roulette();
		// RandInt g = uniform();
		int n = 10;
		// Generating an array of ten random values
		int[] x = g.generate(n);
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