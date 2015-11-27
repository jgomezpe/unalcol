package optimization;
import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.blackbox.BlackBoxArityOne;
import unalcol.optimization.blackbox.BlackBoxSearch;
import unalcol.optimization.blackbox.BlackBoxTracer;
import unalcol.optimization.blackbox.MLPBlackBoxFunction;
import unalcol.optimization.hillclimbing.HillClimbing;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.AdaptMutationIntensity;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.optimization.real.testbed.Schwefel;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.SolutionDescriptors;
import unalcol.search.single.IterativeSinglePointSearch;
import unalcol.search.space.Space;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class BlackBoxTest {
	public static void real(){
		// Search Space definition
		int DIM = 10;
		double min = -5.12;
		double max = 5.12;
    	Space<double[]> space = new HyperCube( DIM, min, max );
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(DIM/2); // It can be set to null if the mutation operator is applied to every component of the solution array
    	AdaptMutationIntensity adapt = null; //new OneFifthRule(1000, 0.9); // It can be set to null if no mutation adaptation is required
    	IntensityMutation variation = new IntensityMutation( 0.1, random, pick, adapt );
        
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Rastrigin();		
        Goal<double[]> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
    	
        // Search method
        int MAXITERS = 100;
        boolean neutral = true; // Accepts movements when having same function value
        HillClimbing<double[]> step = new HillClimbing<double[]>( variation, neutral, 100*MAXITERS );
        BlackBoxSearch<double[]> bb_search = new BlackBoxSearch<>(step, new MLPBlackBoxFunction(space), 2.0, 10000);
        IterativeSinglePointSearch<double[]> search = new IterativeSinglePointSearch<>(bb_search, new ForLoopCondition<>(MAXITERS));
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
    
	public static void real2(){
		// Search Space definition
		int DIM = 10;
		double min = -500;
		double max = 500;
    	Space<double[]> space = new HyperCube( DIM, min, max );
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(6); // It can be set to null if the mutation operator is applied to every component of the solution array
    	AdaptMutationIntensity adapt = new OneFifthRule(20, 0.9); // It can be set to null if no mutation adaptation is required
    	IntensityMutation mutation = new IntensityMutation( 0.1, random, pick, adapt );
    	MLPBlackBoxFunction bb_function = new MLPBlackBoxFunction(space);
        BlackBoxArityOne<double[]> variation = new BlackBoxArityOne<double[]>(3, mutation, bb_function);
        
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Schwefel();		
        Goal<double[]> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
        BlackBoxTracer<double[]> bb_tracer = new BlackBoxTracer<double[]>(space, goal, bb_function, 1.0, 1000);
        Tracer.addTracer(goal, bb_tracer);
    	
        // Search method
        int MAXITERS = 5000;
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
    
    public static void main(String[] args){
    	real2(); // Uncomment if testing real valued functions
    	//binary(); // Uncomment if testing binary valued functions
    }

}
