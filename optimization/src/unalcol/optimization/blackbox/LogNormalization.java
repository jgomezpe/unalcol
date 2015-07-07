package unalcol.optimization.blackbox;

public class LogNormalization {
	protected double MIN;
	public LogNormalization( double MIN ){
		this.MIN = MIN;
	}
	public double apply( double x ){
		return x/MIN;
	}
}
