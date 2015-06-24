

import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.haea.HAEA;
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
import unalcol.optimization.binary.testbed.Deceptive;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.AdaptMutationIntensity;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.PopulationSolutionDescriptors;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.population.variation.Operator;
import unalcol.search.selection.Tournament;
import unalcol.search.space.ArityOne;
import unalcol.search.space.Space;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class HAEATest {
	
	public static void real(){
		// Search Space definition
		int DIM = 10;
		double min = -5.12;
		double max = 5.12;
    	Space<double[]> space = new HyperCube( DIM, min, max );
    	
    	
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Rastrigin();		
        Goal<double[]> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(DIM/2); // It can be set to null if the mutation operator is applied to every component of the solution array
    	AdaptMutationIntensity adapt = new OneFifthRule(500, 0.9); // It can be set to null if no mutation adaptation is required
    	IntensityMutation mutation = new IntensityMutation( 0.1, random, pick, adapt );
    	
    	ArityTwo<double[]> xover = new LinearXOver();
        // Search method
        int POPSIZE = 100;
        int MAXITERS = 100;
		@SuppressWarnings("unchecked")
		Operator<double[]>[] opers = (Operator<double[]>[])new Operator[2];
    	opers[0] = mutation;
    	opers[1] = xover;
    	HaeaOperators<double[]> operators = new SimpleHaeaOperators<double[]>(opers);
        HAEA<double[]> search = new HAEA<double[]>(POPSIZE, operators, new Tournament<double[]>(4), MAXITERS );

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(HaeaStep.class, new WriteHaeaStep<double[]>());
        Descriptors.set(PopulationSolution.class, new PopulationSolutionDescriptors<double[]>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<double[]>());
        Write.set(HaeaOperators.class, write_desc);

        ConsoleTracer tracer = new ConsoleTracer();       
//      Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
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
    	ArityOne<BitArray> mutation = new BitMutation();
    	ArityOne<BitArray> transposition = new Transposition();
    	XOver xover = new XOver();
    	@SuppressWarnings("unchecked")
		Operator<BitArray>[] opers = (Operator<BitArray>[])new Operator[3];
    	opers[0] = mutation;
    	opers[1] = xover;
    	opers[2] = transposition;
    	HaeaOperators<BitArray> operators = new SimpleHaeaOperators<BitArray>(opers);
        
        // Search method
        int POPSIZE = 100;
        int MAXITERS = 10;
        HAEA<BitArray> search = new HAEA<BitArray>(POPSIZE, operators, new Tournament<BitArray>(4), MAXITERS );

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(HaeaStep.class, new WriteHaeaStep<BitArray>());
        Descriptors.set(PopulationSolution.class, new PopulationSolutionDescriptors<BitArray>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<BitArray>());
        Write.set(HaeaOperators.class, write_desc);
        
        ConsoleTracer tracer = new ConsoleTracer();       
//      Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
      Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<BitArray> solution = search.apply(space, goal);
        
        System.out.println( solution.quality() + "=" + solution.value());		
	}
    
    public static void main(String[] args){
    	real(); // Uncomment if testing real valued functions
    	// binary(); // Uncomment if testing binary valued functions
    }
}
