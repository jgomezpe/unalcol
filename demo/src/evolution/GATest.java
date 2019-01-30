package evolution;
import optimization.MethodTest;
import unalcol.evolution.EAFactory;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.XOver;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.Mutation;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.optimization.real.xover.RealArityTwo;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Tournament;
import unalcol.search.space.Space;
import unalcol.testbed.optimization.FunctionTestBed;
import unalcol.testbed.optimization.real.basic.BasicFunctionTestBed;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.object.Tagged;

public class GATest {
	
	public static void real(){
		// Search space
		int DIM=10;
    	// Optimization Function
    	FunctionTestBed<double[]> function = new BasicFunctionTestBed(0,DIM);
    	Space<double[]> space = function.space();    	
    	// Variation
    	Mutation mutation = MethodTest.real_variation(DIM);
		RealArityTwo xover = new LinearXOver();
    	
		// Search method
		int POPSIZE = 100;
		int MAXITERS = 100;
		EAFactory<double[]> factory = new EAFactory<double[]>();
		PopulationSearch<double[],Double> search = 
			factory.generational_ga(POPSIZE, new Tournament<double[],Double>(function, 4), mutation, xover, 0.6, MAXITERS );
		search.setGoal(function);
		// Services
		MethodTest.real_service(function, search);
		MethodTest.population_service(function);
		// Apply the search method
		Tagged<double[]> tagged = search.solve(space);        
		MethodTest.print_function(function);		
	}
    
	public static void binary(){
		// Search Space definition
		Space<BitArray> space = MethodTest.binary_space();
    	
		// Optimization Function
		OptimizationFunction<BitArray> function = MethodTest.binary_f();   	

		// Variation definition
    		BitMutation mutation = MethodTest.binary_mutation();        
    		XOver xover = new XOver();

    		// Search method
    		int POPSIZE = 200;
    		int MAXITERS = 100;
    		EAFactory<BitArray> factory = new EAFactory<BitArray>();
    		PopulationSearch<BitArray,Double> search = 
    				factory.generational_ga(POPSIZE, new Tournament<BitArray,Double>(function,4), mutation, xover, 0.6, MAXITERS );
    		search.setGoal(function);

    		// Apply the search method
    		// Services
    		MethodTest.binary_service(function, search)	;
    		MethodTest.population_service(function);
    		// Apply the search method
    		Tagged<BitArray> tagged = search.solve(space);        
    		MethodTest.print_function(function);		
    		// Remove for general use
    		// Glovito g = new Glovito( solution.object() );
    		// System.out.println(g.toString());
	}
	
	public static void binary2real(){
		// Search space
		int DIM=10;
    	// Optimization Function
    	FunctionTestBed<double[]> function = new BasicFunctionTestBed(0,DIM);
    	HyperCube space = (HyperCube)function.space();    	

		// CodeDecodeMap
		int BITS_PER_DOUBLE = 16; // Number of bits per integer (i.e. per real)
		CodeDecodeMap<BitArray, double[]> map = 
				new BinaryToRealVector(BITS_PER_DOUBLE, space.min(), space.max());

		// Variation definition in the binary space
		BitMutation mutation = MethodTest.binary_mutation();
		XOver xover = new XOver();

		// Search method
		int POPSIZE = 100;
		int MAXITERS = 10;
		EAFactory<BitArray> factory = new EAFactory<BitArray>();
		PopulationSearch<BitArray,Double> bin_search = 
        		factory.generational_ga(POPSIZE, new Tournament<BitArray,Double>(4), mutation, xover, 0.6, MAXITERS );

		// The multilevel search method (moves in the binary space, but computes fitness in the real space)
		MultiLevelSearch<BitArray, double[],Double> search = 
        		new MultiLevelSearch<BitArray,double[],Double>(bin_search, map);
		search.setGoal(function);

		// Services
		MethodTest.binary2real_service(function, search);
		MethodTest.population_service(function);
		// Apply the search method
		Tagged<double[]> tagged = search.solve(space);        
		MethodTest.print_function(function);		
	}
	
	public static void main(String[] args){
		// real(); // Uncomment if testing real valued functions
		 binary(); // Uncomment if testing binary valued functions
		//binary2real(); // Uncomment if you want to try the multi-level search method
		//queen(); // Uncomment if testing integer (queen) value functions
	}
}