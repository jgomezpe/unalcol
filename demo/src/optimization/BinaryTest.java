package optimization;

import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.testbed.optimization.binary.Deceptive;
import unalcol.json.JSON;
import unalcol.optimization.OptimizationFunction;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.object.Tagged;
import unalcol.search.solution.SolutionDescriptors;
import unalcol.search.solution.SolutionWrite;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.Service;

public class BinaryTest extends EncodeTest<BitArray>{
	// ******* Binary space problem ******** //
	public static Space<BitArray> space(){
		// Search Space definition
		int DIM = 120;
    	return new BinarySpace( DIM );
	}
	
	public static OptimizationFunction<BitArray> binary_f(){
    	// Optimization Function
    	OptimizationFunction<BitArray> function = new Deceptive();
    	function.minimize(false); // Set to true if minimizing
    	return function;
	}
	
	@Override
	public Variation_1_1<BitArray> mutation(JSON json){
        return new BitMutation();
	}
	
	public BinaryTest( JSON json ){
		super(json);
        Service.register(new SolutionDescriptors<BitArray>(problem.f()), Tagged.class);
        Service.register(new SolutionWrite<BitArray>(problem.f(),true), Tagged.class);
	}
}
