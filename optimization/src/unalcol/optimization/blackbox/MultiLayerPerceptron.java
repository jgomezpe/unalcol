package unalcol.optimization.blackbox;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.random.util.Shuffle;
import unalcol.types.collection.vector.Vector;

public class MultiLayerPerceptron {
	protected double[][][] weights;
	
	public MultiLayerPerceptron( int[] layer_size ){
		weights = new double[layer_size.length-1][][];
		for( int layer=0; layer<weights.length; layer++){
			weights[layer] = new double[layer_size[layer]][layer_size[layer+1]];
		}
		init();
	}
	
	public final void init(){
		RawGenerator g = new JavaGenerator();
		for( int layer=0; layer<weights.length; layer++ ){
			for( int start=0; start<weights[layer].length; start++ ){
				for( int end=0; end<weights[layer][start].length; end++ ){
					weights[layer][start][end] = 6.0 * g.next() - 3.0;
				}
			}
		}
	}
	
/*
	public static final String LAYER = "Layer";
	protected final double[][] create_layer( Vector<OldDescriptors> descriptors ){
		double[][] layer = new double[descriptors.size()][];
		for(int i=0; i<descriptors.size(); i++){
			layer[i] = descriptors.get(i).get();
		}
		return layer;
	}
	
	public MultiLayerPerceptron( String fileName ){
		Vector<double[][]> layers = new Vector<double[][]>();
		if( Util.TRACE ) System.out.println( "MultiLayerPerceptron, Loading " + fileName + "...");
		try{
			BufferedReader is = new BufferedReader(new FileReader(Util.PATH+Util.MODEL_PATH+fileName));
			Vector<OldDescriptors> list = new Vector<OldDescriptors>();
			int i=0;
			String line;
			while( (line = is.readLine()) != null ){
				if( line.length() > 0 ){
					if( line.indexOf(LAYER) >= 0 ){
						if( list.size()> 0 ) layers.add(create_layer(list));
						list.clear();
					}else{
						list.add(new OldDescriptors(line));
					}	
				}	
				i++;
				if(Util.TRACE && i%10000==0 ) System.out.println(i);
			}
			layers.add(create_layer(list));
			is.close();
			weights = new double[layers.size()][][];
			for( int k=0; k<layers.size(); k++){
				weights[k] = layers.get(k);
			}
			layers.clear();
		}catch(IOException e){
			System.err.println("MultiLayerPerceptron, Loading " + e.getMessage());
		}						
	}
	
	public void save( String fileName ){
		if( Util.TRACE ) System.out.println( "MultiLayerPerceptron, Saving " + fileName + "...");
		try{
			FileWriter out = new FileWriter( Util.MODEL_PATH + fileName );
			out.write(toString());
			out.close();
		}catch( IOException e){
			System.err.println("MultiLayerPerceptron, Saving " + e.getMessage());
		}		
		
	}
*/	
	protected double[] propagate( int layer, double[] input ){
		if( input.length == weights[layer].length ){
			double[] output = new double [weights[layer][0].length];
			for( int end=0; end<output.length; end++){
				for( int start=0; start<weights[layer].length; start++){
					output[end] += input[start]*weights[layer][start][end];
				}
				output[end] = 1.0/(1.0+Math.exp(-output[end]));
			}
			return output;
		}
		return null;
	}
	
	public double[] propagate( double[] input ){
		for( int layer=0; layer<weights.length; layer++ ){
			input = propagate(layer, input);
		}
		return input;
	}
	
	public Vector<double[]> propagate( Vector<double[]> input ){
		Vector<double[]> output = new Vector<double[]>();
		for( double[] x : input ){
			output.add(propagate(x));
		}
		return output;
	}
	
	protected double[] back_propagate_error( int layer, double[] output, double[] error ){
		double[] back_error = new double[weights[layer].length];
		for( int start=0; start<weights[layer].length; start++ ){
			for(int end=0; end<weights[layer][start].length; end++){
				back_error[start] += error[end] * weights[layer][start][end];
			}
			back_error[start] *= output[start]*(1.0-output[start]); 
		}
		return back_error;
	}
	
	public double[] back_propagation( double eta, double[] input, double[] desired, double neg_weight ){
		int lastLayer = weights.length;
		double[][] output = new double[lastLayer+1][];
		output[0] = input;
		for( int layer=1; layer<output.length; layer++){
			output[layer] = propagate(layer-1, output[layer-1]);
		}
		double[] abs_error = new double[desired.length];
		double[][] error = new double[output.length][];
		error[lastLayer] = new double[desired.length];
		for( int end=0; end<desired.length; end++){
			abs_error[end] = (desired[end]-output[lastLayer][end]);
			error[lastLayer][end] = output[lastLayer][end]*(1.0-output[lastLayer][end])*abs_error[end];
			if(abs_error[end] < 0 ){
				abs_error[end] = -abs_error[end];
				error[lastLayer][end] *= neg_weight;
			}
		}
		
		for( int layer=lastLayer-1; layer>=0; layer--){
			error[layer] = back_propagate_error(layer, output[layer], error[layer+1]);
		}
		
		for( int layer=0; layer<weights.length; layer++){
			for( int start=0;start<weights[layer].length; start++){
				for( int end=0; end<weights[layer][start].length; end++){
					weights[layer][start][end] += eta*error[layer+1][end]*output[layer][start];
				}
			}
		}
		return abs_error;
	}
	
	public double[] back_propagation( double eta, Vector<double[]> input, Vector<double[]> output){
		return back_propagation(eta, 1.0, input, output);
	}
	
	public double[] back_propagation( double eta, double neg_weight, Vector<double[]> input, Vector<double[]> output){
		@SuppressWarnings("rawtypes")
		Shuffle shuffle = new Shuffle();
		int[] perm = shuffle.apply(input.size());
		double[] err = back_propagation(eta, input.get(perm[0]), output.get(perm[0]), neg_weight);
		for( int i=1; i<input.size(); i++){
			double[] err2 = back_propagation(eta, input.get(perm[i]), output.get(perm[i]), neg_weight);
			for( int k=0; k<err.length; k++ ){
				err[k] += err2[k];
			}
		}
		
		return err;
	}
	
	public int inputs(){
		return weights[0].length;
	}

/*	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		WriteDoubleArray write = new WriteDoubleArray();
		for( int layer=0; layer<weights.length; layer++){
			sb.append("[Layer "+layer+"]\n");			
			for( int start=0; start<weights[layer].length; start++){
				sb.append(write.apply(weights[layer][start]));
				sb.append("\n");
			}
		}
		return sb.toString();
	}
*/		
}
