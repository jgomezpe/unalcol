package optimization;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.method.AdaptOperatorOptimizationFactory;
//import unalcol.optimization.binary.testbed.MaxOnes;
import unalcol.optimization.method.OptimizationFactory;
import unalcol.optimization.integer.MutationIntArray;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.mutation.Mutation;
import unalcol.optimization.real.mutation.OneFifthRule;
//import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.search.Search;
import unalcol.search.local.LocalSearch;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.space.Space;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.Tagged;

public class HillClimbingTest{
	public static void real(){
		// Search space
		int DIM=10;
    	Space<double[]> space = MethodTest.real_space(DIM);    	
    	// Optimization Function
    	OptimizationFunction<double[]> function = MethodTest.real_f();
    	// Variation
    	Mutation variation = MethodTest.real_variation();
        // Search method
        int MAXITERS = 100;
        boolean neutral = true; // Accepts movements when having same function value
        boolean adapt_operator = false; // Set to true if you want adaptation in operator
        LocalSearch<double[],Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<double[],Double> factory = new AdaptOperatorOptimizationFactory<double[],Double>();
        	search = factory.hill_climbing( function, variation, adapt, neutral, MAXITERS );
        }else{
        	OptimizationFactory<double[]> factory = new OptimizationFactory<double[]>();
        	search = factory.hill_climbing( function, variation, neutral, MAXITERS );
        }
        // Services
        MethodTest.real_service(function, search);
        // Apply the search method
        Tagged<double[]> Tagged = search.solve(space);        
        System.out.println(Tagged.get(function));		
	}
    
	// ******* Binary space problem ******** //
	public static void binary(){
		// Search Space definition
		Space<BitArray> space = MethodTest.binary_space();
    	
    	// Optimization Function
    	OptimizationFunction<BitArray> function = MethodTest.binary_f();   	
    	
    	// Variation definition
    	BitMutation variation = MethodTest.binary_mutation();
        
        // Search method
        int MAXITERS = 10000;
        boolean neutral = true; // Accepts movements when having same function value
        boolean adapt_operator = true; //
        LocalSearch<BitArray,Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<BitArray,Double> factory = new AdaptOperatorOptimizationFactory<BitArray,Double>();
        	search = factory.hill_climbing( function, variation, adapt, neutral, MAXITERS );
        }else{
        	OptimizationFactory<BitArray> factory = new OptimizationFactory<BitArray>();
        	search = factory.hill_climbing( function, variation, neutral, MAXITERS );
        }

        // Apply the search method
        // Services
        MethodTest.binary_service(function, search);
        // Apply the search method
        Tagged<BitArray> Tagged = search.solve(space);        
        System.out.println(Tagged.get(function));		
	}
	
	public static void binary2real(){
		// Search Space definition
		int DIM=10;
    	Space<double[]> space = MethodTest.real_space(DIM);

    	// Optimization Function
    	OptimizationFunction<double[]> function = MethodTest.real_f();		
		
        // CodeDecodeMap
        int BITS_PER_DOUBLE = 16; // Number of bits per integer (i.e. per real)
        CodeDecodeMap<BitArray, double[]> map = 
        		new BinaryToRealVector(BITS_PER_DOUBLE, MethodTest.min(DIM), MethodTest.max(DIM));

    	// Variation definition in the binary space
    	BitMutation variation = MethodTest.binary_mutation();
        
        // Search method in the binary space
        int MAXITERS = 10000;
        boolean neutral = true; // Accepts movements when having same function value
        boolean adapt_operator = true; //
        LocalSearch<BitArray,Double> bin_search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<BitArray,Double> factory = new AdaptOperatorOptimizationFactory<BitArray,Double>();
        	bin_search = factory.hill_climbing( null, variation, adapt, neutral, MAXITERS );
        }else{
        	OptimizationFactory<BitArray> factory = new OptimizationFactory<BitArray>();
        	bin_search = factory.hill_climbing( null, variation, neutral, MAXITERS );
        }

        // The multilevel search method (moves in the binary space, but computes fitness in the real space)
        Search<double[], Double> search = new MultiLevelSearch<>(bin_search, map);
        search.setGoal(function);
        
        // Services
        MethodTest.binary2real_service(function, search);
        // Apply the search method
        Tagged<double[]> Tagged = search.solve(space);        
        System.out.println(Tagged.get(function));		
	}
	
	public static void queen(){
		// It is the well-known problem of setting n-queens in a chess board without attacking among them
		// Search Space definition
		int DIM = 8; // Board size		
		Space<int[]> space = MethodTest.queen_space(DIM);
    	// Optimization Function
    	OptimizationFunction<int[]> function = MethodTest.queen_f();		    	
    	// Variation definition
    	MutationIntArray variation = MethodTest.queen_variation(DIM);
        
        // Search method
        int MAXITERS = 200;
        boolean neutral = true; // Accepts movements when having same function value
        boolean adapt_operator = true; //
        LocalSearch<int[],Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<int[],Double> factory = new AdaptOperatorOptimizationFactory<int[],Double>();
        	search = factory.hill_climbing( function, variation, adapt, neutral, MAXITERS );
        }else{
        	OptimizationFactory<int[]> factory = new OptimizationFactory<int[]>();
        	search = factory.hill_climbing( function, variation, neutral, MAXITERS );
        }

        // Tracking the goal evaluations
        // Apply the search method
        // Services
        MethodTest.queen_service(function, search);
        // Apply the search method
        Tagged<int[]> Tagged = search.solve(space);        
        System.out.println(Tagged.get(function));		
	}
    
    public static void main(String[] args){
    	real(); // Uncomment if testing real valued functions
    	//binary(); // Uncomment if testing binary valued functions
    	//binary2real(); // Uncomment if you want to try the multi-level search method
    	//queen(); // Uncomment if testing queens (integer) value functions
    }
}