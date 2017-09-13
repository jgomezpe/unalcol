package evolution;


import optimization.MethodTest;
import unalcol.Tagged;
import unalcol.evolution.EAFactory;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.evolution.haea.SimpleHaeaOperatorsDescriptor;
import unalcol.evolution.haea.WriteHaeaStep;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.Transposition;
import unalcol.optimization.binary.XOver;
//import unalcol.optimization.binary.testbed.Deceptive;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.mutation.Mutation;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.optimization.real.xover.RealArityTwo;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Tournament;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.types.collection.bitarray.BitArray;

public class HAEATest {
	
	@SuppressWarnings("rawtypes")
	public static void haea_service(){
		ServicePool service = (ServicePool)Service.get();
        service.register( new WriteHaeaStep(), HaeaStep.class);
        service.register( new SimpleHaeaOperatorsDescriptor<double[]>(),HaeaOperators.class);
	}
	
	public static void real(){
		// Search space
		int DIM=10;
		Space<double[]> space = MethodTest.real_space(DIM);    	
		// Optimization Function
		OptimizationFunction<double[]> function = MethodTest.real_f();
    	
		// Variation definition
		Mutation mutation = MethodTest.real_variation();
		RealArityTwo xover = new LinearXOver();
		HaeaOperators<double[]> operators = new SimpleHaeaOperators<double[]>(mutation, xover);
    	
		// Search method
		int POPSIZE = 100;
		int MAXITERS = 100;
		EAFactory<double[]> factory = new EAFactory<double[]>();
		PopulationSearch<double[],Double> search = 
        		factory.HAEA(POPSIZE, operators, new Tournament<double[],Double>(function,4), MAXITERS );

		search.setGoal(function);
		
		// Services
		MethodTest.real_service(function, search);
		MethodTest.population_service(function);
		haea_service();
		
		// Apply the search method
		Tagged<double[]> Tagged = search.solve(space);        
		System.out.println(Tagged.get(function));		
	}
    
	public static void binary(){
		// Search Space definition
		Space<BitArray> space = MethodTest.binary_space();
    	
		// Optimization Function
		OptimizationFunction<BitArray> function = MethodTest.binary_f();   	

		// Variation definition
    		BitMutation mutation = MethodTest.binary_mutation();        
    		XOver xover = new XOver();
    		Transposition transposition = new Transposition();
        	HaeaOperators<BitArray> operators = new SimpleHaeaOperators<BitArray>(mutation, transposition, xover);

    		// Search method
    		int POPSIZE = 100;
    		int MAXITERS = 10;
    		EAFactory<BitArray> factory = new EAFactory<BitArray>();
        
       PopulationSearch<BitArray,Double> search = factory.HAEA(POPSIZE, operators, new Tournament<BitArray,Double>(function,4), MAXITERS );

		search.setGoal(function);

		// Apply the search method
		// Services
		MethodTest.binary_service(function, search)	;
		MethodTest.population_service(function);
		// Apply the search method
		Tagged<BitArray> Tagged = search.solve(space);        
		System.out.println(Tagged.get(function));		
		// Remove for general use
		// Glovito g = new Glovito( solution.object() );
		// System.out.println(g.toString());
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
		BitMutation mutation = MethodTest.binary_mutation();
		XOver xover = new XOver();
		Variation_1_1<BitArray> transposition = new Transposition();
		HaeaOperators<BitArray> operators = new SimpleHaeaOperators<BitArray>(mutation, transposition, xover);
        
		// Search method
		int POPSIZE = 100;
		int MAXITERS = 10;
		EAFactory<BitArray> factory = new EAFactory<BitArray>();
		PopulationSearch<BitArray,Double> bin_search = 
        		factory.HAEA(POPSIZE, operators, new Tournament<BitArray,Double>(4), MAXITERS );

		// The multilevel search method (moves in the binary space, but computes fitness in the real space)
		MultiLevelSearch<BitArray, double[],Double> search = 
        		new MultiLevelSearch<BitArray,double[],Double>(bin_search, map);
		search.setGoal(function);

		// Services
		MethodTest.binary2real_service(function, search);
		MethodTest.population_service(function);
		haea_service();
		
		// Apply the search method
		Tagged<double[]> Tagged = search.solve(space);        
		System.out.println(Tagged.get(function));	
	}
	
	public static void queen(){
/*		// It is the well-known problem of setting n-queens in a chess board without attacking among them
		// Search Space definition
		int DIM = 8; // Board size		
		int[] min = IntArray.create(DIM, 0); // First possible row index
		int[] max = IntArray.create(DIM, DIM-1); // Last possible row index
    	Space<int[]> space = new IntHyperCube( min, max );
    	
    	// Optimization Function
    	OptimizationFunction<int[]> function = new QueenFitness();		
        Goal<int[],Double> goal = new OptimizationGoal<int[]>(function); // minimizing   	
    	
    	// Variation definition
        Variation_1_1<int[]> mutation = new MutationIntArray(DIM);
        Variation_2_2<int[]> xover = new XOverIntArray();
        
    	HaeaOperators<int[]> operators = new SimpleHaeaOperators<int[]>(mutation, xover);
        
        // Search method
        int POPSIZE = 100;
        int MAXITERS = 100;
        EAFactory<int[]> factory = new EAFactory<int[]>();
        PopulationSearch<int[],Double> search = 
        	factory.HAEA(POPSIZE, operators, new Tournament<int[]>(4), MAXITERS );

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(Population.class, write_desc);
        Write.set(HaeaStep.class, new WriteHaeaStep<int[]>());
        Descriptors.set(Population.class, new PopulationDescriptors<int[]>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<int[]>());
        Write.set(HaeaOperators.class, write_desc);
        IntArrayPlainWrite write = new IntArrayPlainWrite(',',false);
        Write.set(int[].class, write);
        ConsoleTracer tracer = new ConsoleTracer();       
//        Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<int[]> solution = search.solve(space, goal);
        
        System.out.println(Write.toString(solution.object()));		
        System.out.println(solution.info(Goal.class.getName()));	*/	
	}
	
    
    public static void main(String[] args){
    	real(); // Uncomment if testing real valued functions
    	// binary(); // Uncomment if testing binary valued functions
    	//binary2real(); // Uncomment if you want to try the multi-level search method
    	//queen(); // Uncomment if testing integer (queen) value functions
    }
}
