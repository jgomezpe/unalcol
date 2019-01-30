package unalcol.testbed.optimization.real.basic;

public class Bohachevsky2 extends Bohachevsky{

	/**
	 * Evaluates the Bohachevsky I function over two real values
	 * @param x1 the first real value argument of the Bohachevsky function
	 * @param x2 the second real value argument of the Bohachevsky function
	 * @return the Bohachevsky value for the given values
	 */
	@Override
	public double eval( double x1, double x2 ){
		return ( x1*x1 + 2*x2*x2 - 0.12*Math.cos(3.0*Math.PI*x1)*Math.cos(4.0*Math.PI*x2) + 0.3 );
	}
}