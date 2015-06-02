import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.AdaptMutationIntensity;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.optimization.simulatedannealing.SimulatedAnnealing;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;


public class SimulatedAnnealingTest {
	
	public static void test_real(){
		// Search Space definition
		int DIM = 10;
		double min = -5.12;
		double max = 5.12;
    	Space<double[]> space = new HyperCube( DIM, min, max );
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other number generator
    	PickComponents pick = new PermutationPick(DIM/2); // It can be set to null
    	AdaptMutationIntensity adapt = new OneFifthRule(100, 0.9); // It can be set to null
    	IntensityMutation variation = new IntensityMutation( 0.1, random, pick, adapt );
        
    	// Optimization Function
    	OptimizationFunction<double[]> function = new Rastrigin();		
        Goal<double[]> goal = new OptimizationGoal<double[]>(function);
    	
        // Search method
        int MAXITERS = 10000;
        SimulatedAnnealing<double[]> search = new SimulatedAnnealing<double[]>(variation, MAXITERS);

        // Tracking the goal evaluations
        ConsoleTracer tracer = new ConsoleTracer(goal);       
        Tracer.register(goal,tracer);
        
        // Apply the search method
        Solution<double[]> solution = search.apply(space, goal);
        
        System.out.println(solution.quality());		
	}
    
    public static void main(String[] args){
    	test_real();
    }

}
