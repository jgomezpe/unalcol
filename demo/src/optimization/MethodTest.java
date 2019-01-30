package optimization;

import unalcol.descriptors.WriteDescriptors;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.testbed.optimization.FunctionTestBed;
import unalcol.testbed.optimization.binary.Deceptive;
import unalcol.optimization.integer.IntHyperCube;
import unalcol.optimization.integer.MutationIntArray;
import unalcol.testbed.optimization.integer.QueenFitness;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.Mutation;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.testbed.optimization.real.basic.Rastrigin;
import unalcol.random.real.RandDouble;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Search;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.solution.SolutionDescriptors;
import unalcol.search.solution.SolutionWrite;
import unalcol.search.space.Space;
import unalcol.services.ProvidersSet;
import unalcol.services.Service;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.tracer.VectorTracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.Vector;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;
import unalcol.types.object.Tagged;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class MethodTest {
	// ******* any ******** //
	public static void service(OptimizationFunction<?> function, Search<?, Double> search){
        // Tracking the goal evaluations
		Tracer t = new ConsoleTracer();
		t.start();
		Service.register(t, search);
		t = new VectorTracer();
		t.start();
		Service.register(t, function);
	}
	
	// ******* Real space problem ******** //
	
	public static Mutation real_variation(int D){
    	// Variation definition
    	RandDouble random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(6); // It can be set to null if the mutation operator is applied to every component of the Tagged array
    	return new IntensityMutation( 0.1, random, pick );
	}
	
	public static void real_service(OptimizationFunction<double[]> function, Search<double[], Double> search){
		service( function, search );
        Service.register(new SolutionDescriptors<double[]>(function), Tagged.class);
        Service.register(new DoubleArrayPlainWrite(',',false), double[].class);
        Service.register(new SolutionWrite<double[]>(function,true), Tagged.class);
	}
        
	// ******* Binary space problem ******** //
	public static Space<BitArray> binary_space(){
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
	
	public static BitMutation binary_mutation(){
        return new BitMutation();
	}
	
	public static void binary_service( OptimizationFunction<BitArray> function, 
			Search<BitArray,Double> search){ 
		service( function, search );
        Service.register(new DoubleArrayPlainWrite(',',false), double[].class);
        Service.register(new SolutionDescriptors<BitArray>(function), Tagged.class);
        Service.register(new SolutionWrite<BitArray>(function,true), Tagged.class);
	}

	public static void binary2real_service( OptimizationFunction<double[]> function, 
			Search<double[],Double> search){ 
        // Tracking the goal evaluations
		service( function, search );
        Service.register(new SolutionDescriptors<double[]>(function), Tagged.class);
        Service.register(new WriteDescriptors(), Tagged.class);
	}
	
	
	// Queen problem
	public static Space<int[]> queen_space( int DIM ){
		// It is the well-known problem of setting n-queens in a chess board without attacking among them
		// Search Space definition
		int[] min = IntArray.create(DIM, 0); // First possible row index
		int[] max = IntArray.create(DIM, DIM-1); // Last possible row index
    	return new IntHyperCube( min, max );
	}
	
	public static OptimizationFunction<int[]> queen_f(){
    	// Optimization Function
    	OptimizationFunction<int[]> function = new QueenFitness();
    	function.minimize(true);
    	return function;
	}
	
	public static MutationIntArray queen_variation(int DIM){
    	// Variation definition
    	return new MutationIntArray(DIM);
	}
	
	public static void queen_service(OptimizationFunction<int[]> function, Search<int[], Double> search){
		// Tracking the goal evaluations
        Service.register(new SolutionDescriptors<int[]>(function), Tagged.class);
        Service.register(new IntArrayPlainWrite(',',false), int[].class);
        Service.register(new WriteDescriptors(), Tagged.class);
	}
	
	public static void population_service(OptimizationFunction<?> function ){
		@SuppressWarnings("rawtypes")
		PopulationDescriptors pd= new PopulationDescriptors();
		//pd.setGoal(function);
		Service.register(pd, Tagged[].class);
		Service.register(new WriteDescriptors(), Tagged[].class);
	}	
	
	public static void print_function(OptimizationFunction<?> function){
		try{
			ProvidersSet tracers = Service.providers(Tracer.class, function);
			Tracer s = (Tracer)tracers.get("VectorTracer");
			@SuppressWarnings("unchecked")
			Vector<Object[]> v = (Vector<Object[]>)s.get();
			Object[] f = (Object[])v.get(0);
			// The fitness value is located as the second element in the array (the first one is the object)
			double bf = (Double)(f[1]);
			for( int i=0; i<v.size(); i++ ){
				f = (Object[])v.get(i);
				double cf = (Double)(f[1]);
				if( function.order().compare(bf, cf) < 0 ) bf = cf;
				System.out.println(i+" "+bf);
			}
		}catch(Exception e){ e.printStackTrace(); }
	}	
}