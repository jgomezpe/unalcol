package optimization;

import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.testbed.Deceptive;
//import unalcol.optimization.binary.testbed.MaxOnes;
import unalcol.optimization.hillclimbing.HillClimbing;
import unalcol.optimization.integer.IntHyperCube;
import unalcol.optimization.integer.MutationIntArray;
import unalcol.optimization.integer.testbed.QueenFitness;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.AdaptMutationIntensity;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
//import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.optimization.real.testbed.Schwefel;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.SolutionDescriptors;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.space.Space;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class HillClimbingTest{
	
	public static void real(){
		// Search Space definition
		int DIM = 10;
		double[] min = DoubleArray.create(DIM, -500.0);
		double[] max = DoubleArray.create(DIM, 500.0);
    	Space<double[]> space = new HyperCube( min, max );
    	
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Schwefel();		
        Goal<double[]> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(6); // It can be set to null if the mutation operator is applied to every component of the solution array
    	AdaptMutationIntensity adapt = new OneFifthRule(20, 0.9); // It can be set to null if no mutation adaptation is required
    	IntensityMutation variation = new IntensityMutation( 0.1, random, pick, adapt );
        
        // Search method
        int MAXITERS = 10000;
        boolean neutral = true; // Accepts movements when having same function value
        HillClimbing<double[]> search = new HillClimbing<double[]>( variation, neutral, MAXITERS );

        // Tracking the goal evaluations
        SolutionDescriptors<double[]> desc = new SolutionDescriptors<double[]>();
        Descriptors.set(Solution.class, desc);
        DoubleArrayPlainWrite write = new DoubleArrayPlainWrite(false);
        Write.set(double[].class, write);
        WriteDescriptors w_desc = new WriteDescriptors();
        Write.set(Solution.class, w_desc);
        
        ConsoleTracer tracer = new ConsoleTracer();       
//        Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<double[]> solution = search.apply(space, goal);
        
        System.out.println(solution.quality());		
	}
    
	public static void binary(){
		// Search Space definition
		int DIM = 120;
    	Space<BitArray> space = new BinarySpace( DIM );
    	
    	// Optimization Function
    	OptimizationFunction<BitArray> function = new Deceptive();		
        Goal<BitArray> goal = new OptimizationGoal<BitArray>(function, false); // maximizing, remove the parameter false if minimizing   	
    	
    	// Variation definition
    	BitMutation variation = new BitMutation();
        
        // Search method
        int MAXITERS = 10000;
        boolean neutral = true; // Accepts movements when having same function value
        HillClimbing<BitArray> search = new HillClimbing<BitArray>( variation, neutral, MAXITERS );

        // Tracking the goal evaluations
        ConsoleTracer tracer = new ConsoleTracer();       
//      Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<BitArray> solution = search.apply(space, goal);
        
        System.out.println( solution.quality() + "=" + solution.value());		
	}
	
	public static void binary2real(){
		// Search Space definition
		int DIM = 10;
		double[] min = DoubleArray.create(DIM, -500.0);
		double[] max = DoubleArray.create(DIM, 500.0);
    	Space<double[]> space = new HyperCube( min, max );

    	// Optimization Function
    	OptimizationFunction<double[]> function = new Schwefel();		
        Goal<double[]> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
		
        // CodeDecodeMap
        int BITS_PER_DOUBLE = 16; // Number of bits per integer (i.e. per real)
        CodeDecodeMap<BitArray, double[]> map = new BinaryToRealVector(BITS_PER_DOUBLE, min, max);

    	// Variation definition in the binary space
    	BitMutation variation = new BitMutation();
        
        // Search method in the binary space
        int MAXITERS = 10000;
        boolean neutral = true; // Accepts movements when having same function value
        HillClimbing<BitArray> bin_search = new HillClimbing<BitArray>( variation, neutral, MAXITERS );

        // The multilevel search method (moves in the binary space, but computes fitness in the real space)
        MultiLevelSearch<BitArray, double[]> search = new MultiLevelSearch<>(bin_search, map);
        
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
        Solution<double[]> solution = search.apply(space, goal);
        
        System.out.println( solution.quality() + "=" + solution.value());		
        
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
        Goal<int[]> goal = new OptimizationGoal<int[]>(function); // minimizing   	
    	
    	// Variation definition
    	MutationIntArray variation = new MutationIntArray(DIM);
        
        // Search method
        int MAXITERS = 200;
        boolean neutral = true; // Accepts movements when having same function value
        HillClimbing<int[]> search = new HillClimbing<int[]>( variation, neutral, MAXITERS );

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
        Solution<int[]> solution = search.apply(space, goal);
        
        System.out.println( solution.quality() + "=" + solution.value());		
	}
    
    public static void main(String[] args){
    	real(); // Uncomment if testing real valued functions
    	//binary(); // Uncomment if testing binary valued functions
    	//binary2real(); // Uncomment if you want to try the multi-level search method
    	//queen(); // Uncomment if testing queens (integer) value functions
    }
}