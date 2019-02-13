package optimization.hillclimbing;
import optimization.EncodeTest;
import optimization.MethodTest;
import optimization.RealsTest;
import unalcol.optimization.Problem;
import unalcol.optimization.method.AdaptOperatorOptimizationFactory;
import unalcol.optimization.method.OptimizationFactory;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.Mutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.search.Search;
import unalcol.search.local.LocalSearch;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation;
import unalcol.search.variation.Variation_1_1;
import unalcol.testbed.optimization.real.lsgo.LSGOFunction;
import unalcol.types.object.Tagged;

public class HCRealsTest<T> extends MethodTest<T> {
	@Override
	public Search<T, Double> search(int MAXITERS, Problem<T> problem, Object... args) {
		// Search space
		RealsTest encode = new RealsTest(problem, MAXITERS);
		
    	// Variation
		int MUTATION = (Integer)args[2];
		Object[] obj = new Object[args.length-2];
		for( int i=0; i<obj.length; i++ ) obj[i] = args[i+2];
		
		Variation_1_1<T> variation = encode.mutation(MUTATION, obj);

    	// Search method
        boolean adapt_operator = (Boolean)args[0]; // Set to true if you want adaptation in operator
        boolean neutral = (Boolean)args[1]; // Accepts movements when having same function value
        LocalSearch<T,Double> search;
        if( adapt_operator ){
        	OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
        	AdaptOperatorOptimizationFactory<T,Double> factory = new AdaptOperatorOptimizationFactory<T,Double>();
        	search = factory.hill_climbing( problem.f(), variation, adapt, neutral, MAXITERS );
        }else{
        	OptimizationFactory<T> factory = new OptimizationFactory<T>();
        	search = factory.hill_climbing( problem.f(), variation, neutral, MAXITERS );
        }
		return search;
	}

	public static void main( String[] args ){
		args = new String[]{"basic", "4","0","0.1","15000", "10"};
		int problem = Integer.parseInt(args[1]); 
		System.out.println(args[1]);
		int mutation = Integer.parseInt(args[2]); 
		double sigma = Double.parseDouble(args[3]); 
		int iters = Integer.parseInt(args[4]); 
		if( args[0].equals("basic") ){ // A basic optimization function
			int dim = Integer.parseInt(args[5]);
			basic( problem, dim, mutation, sigma, iters );
		}else lsgo( problem, mutation, sigma, iters );
	}

}
