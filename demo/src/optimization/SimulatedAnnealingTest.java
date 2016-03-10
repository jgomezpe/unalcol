package optimization;
import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.testbed.MaxOnes;
import unalcol.optimization.integer.IntHyperCube;
import unalcol.optimization.integer.MutationIntArray;
import unalcol.optimization.integer.testbed.QueenFitness;
import unalcol.optimization.method.AdaptOperatorOptimizationFactory;
import unalcol.optimization.method.OptimizationFactory;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.reflect.tag.TaggedObject;
import unalcol.search.Goal;
import unalcol.search.local.LocalSearch;
import unalcol.search.solution.Solution;
import unalcol.search.solution.SolutionDescriptors;
import unalcol.search.solution.SolutionWrite;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.space.Space;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;


public class SimulatedAnnealingTest {
	
	public static void real(){
		// Search Space definition
		int DIM = 10;
		double[] min = DoubleArray.create(DIM, -5.12);
		double[] max = DoubleArray.create(DIM, 5.12);
    	Space<double[]> space = new HyperCube( min, max );
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(DIM/2); // It can be set to null if the mutation operator is applied to every component of the solution array
    	IntensityMutation variation = new IntensityMutation( 0.1, random, pick );
        
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Rastrigin();		
        Goal<double[],Double> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing
    	
        // Search method
        int MAXITERS = 10000;
        boolean adapt_operator = true; //
        LocalSearch<double[],Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<double[],Double> factory = new AdaptOperatorOptimizationFactory<double[],Double>();
        	search = factory.simulated_annealing( variation, adapt, MAXITERS );
        }else{
        	OptimizationFactory<double[]> factory = new OptimizationFactory<double[]>();
        	search = factory.simulated_annealing( variation, MAXITERS );
        }

        // Tracking the goal evaluations
        SolutionDescriptors<double[]> desc = new SolutionDescriptors<double[]>();
        Descriptors.set(TaggedObject.class, desc);
        DoubleArrayPlainWrite write = new DoubleArrayPlainWrite(false);
        Write.set(double[].class, write);
        SolutionWrite<double[]> w_desc = new SolutionWrite<double[]>(true);
        Write.set(TaggedObject.class, w_desc);

        ConsoleTracer tracer = new ConsoleTracer();       
        Tracer.addTracer(goal,tracer);
        
        // Apply the search method
        TaggedObject<double[]> solution = search.solve(space, goal);
        
        System.out.println(solution.info(Goal.class.getName()));		
	}
    
	public static void binary(){
		// Search Space definition
		int DIM = 100;
    	Space<BitArray> space = new BinarySpace( DIM );
    	
    	// Variation definition
    	BitMutation variation = new BitMutation();
        
    	// Optimization Function
    	OptimizationFunction<BitArray> function = new MaxOnes();		
        Goal<BitArray,Double> goal = new OptimizationGoal<BitArray>(function, false); // maximizing, remove the parameter false if minimizing   	
    	
        // Search method
        int MAXITERS = 10000;
        boolean adapt_operator = true; //
        LocalSearch<BitArray,Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<BitArray,Double> factory = new AdaptOperatorOptimizationFactory<BitArray,Double>();
        	search = factory.simulated_annealing( variation, adapt, MAXITERS );
        }else{
        	OptimizationFactory<BitArray> factory = new OptimizationFactory<BitArray>();
        	search = factory.simulated_annealing( variation, MAXITERS );
        }

        // Tracking the goal evaluations
        ConsoleTracer tracer = new ConsoleTracer();       
        Tracer.addTracer(goal,tracer);
        
        // Apply the search method
        TaggedObject<BitArray> solution = search.solve(space, goal);
        
        System.out.println( solution.info(Goal.class.getName()) + "=" + solution.object());		        
	}    

	public static void binary2real(){
		// Search Space definition
		int DIM = 10;
		double[] min = DoubleArray.create(DIM, -5.12);
		double[] max = DoubleArray.create(DIM, 5.12);
    	Space<double[]> space = new HyperCube( min, max );

    	// Optimization Function
    	OptimizationFunction<double[]> function = new Rastrigin();		
        Goal<double[],Double> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
		
        // CodeDecodeMap
        int BITS_PER_DOUBLE = 16; // Number of bits per integer (i.e. per real)
        CodeDecodeMap<BitArray, double[]> map = new BinaryToRealVector(BITS_PER_DOUBLE, min, max);

    	// Variation definition in the binary space
    	BitMutation variation = new BitMutation();
        
        // Search method in the binary space
        int MAXITERS = 10000;
        boolean adapt_operator = true; //
        LocalSearch<BitArray,Double> bin_search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<BitArray,Double> factory = new AdaptOperatorOptimizationFactory<BitArray,Double>();
        	bin_search = factory.simulated_annealing( variation, adapt, MAXITERS );
        }else{
        	OptimizationFactory<BitArray> factory = new OptimizationFactory<BitArray>();
        	bin_search = factory.simulated_annealing( variation, MAXITERS );
        }

        // The multilevel search method (moves in the binary space, but computes fitness in the real space)
        MultiLevelSearch<BitArray, double[], Double> search = new MultiLevelSearch<>(bin_search, map);
        
        // Tracking the goal evaluations
        SolutionDescriptors<double[]> desc = new SolutionDescriptors<double[]>();
        Descriptors.set(Solution.class, desc);
        DoubleArrayPlainWrite write = new DoubleArrayPlainWrite(false);
        Write.set(double[].class, write);
        //WriteDescriptors w_desc = new WriteDescriptors();
        //Write.set(Solution.class, w_desc);

        ConsoleTracer tracer = new ConsoleTracer();       
        Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
//        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        TaggedObject<double[]> solution = search.solve(space, goal);
        
        System.out.println( solution.info(Goal.class.getName()) + "=" + solution.object());		        
        
	}

	public static void queen(){
		// It is the well-known problem of setting n-queens in a chess board without attacking among them
		// Search Space definition
		int DIM = 8; // Board size		
		int[] min = IntArray.create(DIM, 0); // First possible row index
		int[] max = IntArray.create(DIM, DIM-1); // Last possible row index
    	Space<int[]> space = new IntHyperCube( min, max );
    	
    	// Optimization Function
    	OptimizationFunction<int[]> function = new QueenFitness();		
        Goal<int[],Double> goal = new OptimizationGoal<int[]>(function); // minimizing   	
    	
    	// Variation definition
    	MutationIntArray variation = new MutationIntArray(DIM);
        
        // Search method
        int MAXITERS = 200;
        boolean adapt_operator = true; //
        LocalSearch<int[],Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<int[],Double> factory = new AdaptOperatorOptimizationFactory<int[],Double>();
        	search = factory.simulated_annealing( variation, adapt, MAXITERS );
        }else{
        	OptimizationFactory<int[]> factory = new OptimizationFactory<int[]>();
        	search = factory.simulated_annealing( variation, MAXITERS );
        }

        // Tracking the goal evaluations
        SolutionDescriptors<int[]> desc = new SolutionDescriptors<int[]>();
        Descriptors.set(Solution.class, desc);
        IntArrayPlainWrite write = new IntArrayPlainWrite(',',false);
        Write.set(int[].class, write);
        WriteDescriptors w_desc = new WriteDescriptors();
        Write.set(Solution.class, w_desc);
        ConsoleTracer tracer = new ConsoleTracer();       
//      Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        TaggedObject<int[]> solution = search.solve(space, goal);
        
        System.out.println( solution.info(Goal.class.getName()) + "=" + solution.object());		        
	}
    
	
    public static void main(String[] args){
    	real(); // Uncomment if testing real valued functions
    	//binary(); // Uncomment if testing binary valued functions
    	//binary2real(); // Uncomment if you want to try the multi-level search method
    	//queen(); // Uncomment if testing queens (integer) value functions
    }

}
