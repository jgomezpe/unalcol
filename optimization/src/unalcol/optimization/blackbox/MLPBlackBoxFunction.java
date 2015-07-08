package unalcol.optimization.blackbox;

import unalcol.optimization.real.HyperCube;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.LinealScale;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.RealVectorLinealScale;

public class MLPBlackBoxFunction extends BlackBoxFunction<double[]> {
	protected RealVectorLinealScale scale=null;
	protected MultiLayerPerceptron mlp;
	protected HyperCube spc = null;
	
	public MLPBlackBoxFunction(Space<double[]> space) {
		spc = (HyperCube)space;
		int n = spc.max().length;
		scale = new RealVectorLinealScale(spc.min(), spc.max(), DoubleArray.create(n, 0.1), DoubleArray.create(n, 0.9));
		mlp = new MultiLayerPerceptron(new int[]{n,20,1});
		min = new double[n];
		max = new double[n];
		length = new double[n];
		zero = DoubleArray.create(min.length, 0.1);
		one = DoubleArray.create(max.length, 0.9);
	}
	
	@Override
	public Double apply(double[] x) {
		// TODO Auto-generated method stub
		if(scale != null){
			double value = mlp.propagate(scale.apply(x))[0];
			//System.out.println(value);
			return value;
		}
		return 0.0;
	}

	protected double[] min;
	protected double[] max;
	protected double[] length;
	protected double[] zero;
	protected double[] one;
	
	@Override
	public void train(Vector<Solution<double[]>> solution, Space<double[]> space, Goal<double[]> goal, Object... args) {
		if( space != spc ){
			spc = (HyperCube)space;
			int n = spc.max().length;
			scale = new RealVectorLinealScale(spc.min(), spc.max(), DoubleArray.create(n, 0.1), DoubleArray.create(n, 0.9));
			mlp = new MultiLayerPerceptron(new int[]{n,20,1});
			min = new double[n];
			max = new double[n];
			length = new double[n];
			zero = DoubleArray.create(min.length, 0.1);
			one = DoubleArray.create(max.length, 0.9);
		}	
		double MIN = solution.get(0).quality();
		double MAX = MIN;
		double[] x = solution.get(0).value();
		for( int k=0; k<x.length; k++ ){
			min[k] = x[k];
			max[k] = x[k];			
		}
		for( int i=1; i<solution.size(); i++ ){
			x = solution.get(i).value();
			for( int k=0; k<x.length; k++ ){
				if( x[k] < min[k] ){
					min[k] = x[k];
				}else{
					if( max[k] < x[k] ){
						max[k] = x[k];
					}
				}
			}
			// input.add(scale.apply(solution.get(i).value()));
			double out = solution.get(i).quality();
			if( out < MIN ){
				MIN = out;
			}else{
				if( MAX < out ){
					MAX = out;
				}
			}
		}
		if( solution.size() > 1 ){
			for( int k=0; k<length.length; k++){
				length[k] = (one[k]-zero[k])/(max[k]-min[k]);
			}
		}
		//scale = new RealVectorLinealScale(min, max, zero, one);
		Vector<double[]> input = new Vector<double[]>();
		if( solution.size() > 1 ){
			for( int i=0; i<solution.size(); i++ ){
				double[] y = solution.get(i).value();
				double[] v = zero.clone(); // v = scale.apply(y);
				for( int k=0; k<min.length; k++ ){
					v[k] += (y[k] - min[k])*length[k];
				}
				input.add(v);
			}
		}else{
			input.add(solution.get(0).value());
		}
//		System.out.println("["+MIN+","+MAX+"]");
		LogNormalization ln = new LogNormalization(MIN/1.1);
		LinealScale nn_scale = new LinealScale(ln.apply(MIN/1.1), ln.apply(MAX), 0.1,  0.9);
//		LinealScale nn_scale = new LinealScale(MIN/10, MAX+MIN/10, 0.1,  0.9);
		Vector<double[]> output = new Vector<double[]>();
		for( int i=0; i<solution.size(); i++ ){
			if( MIN == MAX ){
				output.add(new double[]{0.5});
			}else{
				output.add(new double[]{nn_scale.apply(ln.apply(solution.get(i).quality()))});
//				output.add(new double[]{nn_scale.apply(-solution.get(i).quality())});
			}
		}
		double eta = (double)args[0];
		int ITERS = Math.min(50*input.size(), 1000);
		for( int i=1; i<=ITERS; i++ ){
			//double[] error =
					mlp.back_propagation(eta, 0.10, input, output);
			if( i%10==0){
				
				if( i%100==0 ){
					//System.out.println(i + " " + error[0] + "#" +error[0]/input.size());
					eta *= 0.975;
					//System.out.println( "eta:" + eta );
				}	
			}
		}
		output.clear();
		input.clear();
	}

}
