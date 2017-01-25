package evolution;


import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.evolution.haea.SimpleHaeaOperatorsDescriptor;
import unalcol.evolution.haea.WriteHaeaStep;
import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.Transposition;
import unalcol.optimization.binary.XOver;
//import unalcol.optimization.binary.testbed.Deceptive;
import unalcol.optimization.integer.IntHyperCube;
import unalcol.optimization.integer.MutationIntArray;
import unalcol.optimization.integer.XOverIntArray;
import unalcol.optimization.integer.testbed.QueenFitness;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.optimization.real.xover.RealArityTwo;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.population.Population;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Tournament;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.search.variation.Variation_2_2;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.integer.array.IntArray;
import unalcol.types.integer.array.IntArrayPlainWrite;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class HAEATest {
	
	public static void real(){
		// Search Space definition
		int DIM = 10;
		double[] min = DoubleArray.create(DIM, -5.12);
		double[] max = DoubleArray.create(DIM, 5.12);
    	Space<double[]> space = new HyperCube( min, max );    	
    	
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Rastrigin();		
        Goal<double[],Double> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(DIM/2); // It can be set to null if the mutation operator is applied to every component of the solution array
    	IntensityMutation mutation = new IntensityMutation( 0.1, random, pick );
    	RealArityTwo xover = new LinearXOver();
    	
        // Search method
        int POPSIZE = 10;
        int MAXITERS = 5;
    	HaeaOperators<double[]> operators = new SimpleHaeaOperators<double[]>(mutation, xover);
        EAFactory<double[]> factory = new EAFactory<double[]>();
        PopulationSearch<double[],Double> search = 
        		factory.HAEA(POPSIZE, operators, new Tournament<double[]>(4), MAXITERS );

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(Population.class, write_desc);
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(HaeaStep.class, new WriteHaeaStep<double[]>());
        Descriptors.set(Population.class, new PopulationDescriptors<double[]>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<double[]>());
        Write.set(HaeaOperators.class, write_desc);

        ConsoleTracer tracer = new ConsoleTracer();       
//        Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
//        FileTracer file = new FileTracer("prueba-lab.txt");
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
//        Tracer.addTracer(search, file);
        // Apply the search method
        Solution<double[]> solution = search.solve(space, goal);
        
        System.out.println(solution.info(Goal.class.getName()));		
	}
    
	public static void binary(){
		// Search Space definition
		int DIM = 24;
    	Space<BitArray> space = new BinarySpace( DIM );
    	
    	// Optimization Function
    	OptimizationFunction<BitArray> function = new GlovitoFitness();		
        Goal<BitArray,Double> goal = new OptimizationGoal<BitArray>(function, false); // maximizing, remove the parameter false if minimizing   	
    	
    	// Variation definition
    	Variation_1_1<BitArray> mutation = new BitMutation();
    	Variation_1_1<BitArray> transposition = new Transposition();
    	XOver xover = new XOver();
    	HaeaOperators<BitArray> operators = new SimpleHaeaOperators<BitArray>(mutation, transposition, xover);
        
        // Search method
        int POPSIZE = 100;
        int MAXITERS = 10;
        EAFactory<BitArray> factory = new EAFactory<BitArray>();
        PopulationSearch<BitArray,Double> search = 
        		factory.HAEA(POPSIZE, operators, new Tournament<BitArray>(4), MAXITERS );

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(HaeaStep.class, new WriteHaeaStep<BitArray>());
        Descriptors.set(Population.class, new PopulationDescriptors<BitArray>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<BitArray>());
        Write.set(HaeaOperators.class, write_desc);
        
        ConsoleTracer tracer = new ConsoleTracer();       
//      Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<BitArray> solution = search.solve(space, goal);
        
        System.out.println(solution.info(Goal.class.getName()));		
        // Remove for general use
        Glovito g = new Glovito( solution.object() );
        System.out.println(g.toString());
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

    	// Variation definition
    	Variation_1_1<BitArray> mutation = new BitMutation();
    	Variation_1_1<BitArray> transposition = new Transposition();
    	XOver xover = new XOver();
		HaeaOperators<BitArray> operators = new SimpleHaeaOperators<BitArray>(mutation, transposition, xover);
        
        // Search method
        int POPSIZE = 100;
        int MAXITERS = 10;
        EAFactory<BitArray> factory = new EAFactory<BitArray>();
        PopulationSearch<BitArray,Double> bin_search = 
        		factory.HAEA(POPSIZE, operators, new Tournament<BitArray>(4), MAXITERS );

        // The multilevel search method (moves in the binary space, but computes fitness in the real space)
        MultiLevelSearch<BitArray, double[],Double> search = new MultiLevelSearch<>(bin_search, map);

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(HaeaStep.class, new WriteHaeaStep<BitArray>());
        Descriptors.set(Population.class, new PopulationDescriptors<BitArray>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<BitArray>());
        Write.set(HaeaOperators.class, write_desc);
        
        ConsoleTracer tracer = new ConsoleTracer();       
        Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        //Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<double[]> solution = search.solve(space, goal);
        
        System.out.println(solution.info(Goal.class.getName()));		
		
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
        System.out.println(solution.info(Goal.class.getName()));		
	}
	
    
    public static void main(String[] args){
    	real(); // Uncomment if testing real valued functions
    	// binary(); // Uncomment if testing binary valued functions
    	//binary2real(); // Uncomment if you want to try the multi-level search method
    	//queen(); // Uncomment if testing integer (queen) value functions
    }
}
