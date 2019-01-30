package unalcol.testbed.optimization.real.lsgo;

import lsgo.*;

import unalcol.optimization.real.HyperCube;
import unalcol.search.space.Space;
import unalcol.testbed.optimization.FunctionTestBed;
import unalcol.types.real.array.DoubleArray;

public class LSGOFunction  extends FunctionTestBed<double[]> {
	protected lsgo.Function f;
	
	public LSGOFunction( int k ) {
		super(k);
		switch( k ){
			case 1: f = new F1(); break;
			case 2: f = new F2(); break;
			case 3: f = new F3(); break;
			case 4: f = new F4(); break;
			case 5: f = new F5(); break;
			case 6: f = new F6(); break;
			case 7: f = new F7(); break;
			case 8: f = new F8(); break;
			case 9: f = new F9(); break;
			case 10: f = new F10(); break;
			case 11: f = new F11(); break;
			case 12: f = new F12(); break;
			case 13: f = new F13(); break;
			case 14: f = new F14(); break;
			case 15: f = new F15(); break;
			case 16: f = new F16(); break;
			case 17: f = new F17(); break;
			case 18: f = new F18(); break;
			case 19: f = new F19(); break;
			case 20: f = new F20(); break;
		}
	}
	
	@Override
	public Double compute(double[] x) { return f.compute(x); }
	
	public Space<double[]> space(){
		int D = f.getDimension();
		double minL = f.getMin();
		double maxL = f.getMax();
		double[] max = DoubleArray.create(D, maxL);
		double[] min = DoubleArray.create(D, minL);
		return new HyperCube(min, max);		
	}
}
