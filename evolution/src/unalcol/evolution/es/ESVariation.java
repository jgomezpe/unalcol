package unalcol.evolution.es;

import unalcol.random.integer.IntUniform;
import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.search.solution.variation.SolutionOperator;
import unalcol.search.space.variation.BuildOne;
import unalcol.search.variation.ArityOneSearchOperator;
import unalcol.search.variation.ParameterizedObject;

public class ESVariation<T,P> implements SolutionOperator<T>{ 
	public static final String PARAMETERS_OPERATOR = "Parameters";
	protected int lambda;
	protected int ro;
	protected BuildOne<T> recombination;
	protected ArityOneSearchOperator<T> mutation;
	protected ParameterizedObject<P> param_mutation;
	protected BuildOne<P> s_recombination;
	protected ArityOneSearchOperator<P> s_mutation;
	
	@SuppressWarnings("unchecked")
	public ESVariation( int lambda, int ro, 
			       		BuildOne<T> y_recombination, ArityOneSearchOperator<T> mutation, 
			       		BuildOne<P> s_recombination, ArityOneSearchOperator<P> s_mutation) {
		this.lambda = lambda;
		this.ro = ro;
		this.recombination = y_recombination;
		this.mutation = mutation;
		this.s_recombination = s_recombination;
    	if( this.mutation instanceof ParameterizedObject ){
    		this.param_mutation = (ParameterizedObject<P>)this.mutation;
    	}else{
    		this.param_mutation = null;
    	}
	}

    /**
     * Gets a subpopulation of lambda individuals that can be used for the marriage process
     * @param population Full Population
     * @return A subpopulation of lambda individuals that can be used for the marriage process
     */
    public int[] select( int mu ){
    	IntUniform g = new IntUniform(mu);
        return g.generate(ro);
    }

    /**
     * Generates a population of offspring individuals following haea rules.
     * @param population The population to be transformed
     * @param replace Replacement mechanism
     * @param f Function to be optimized
     */
	@SuppressWarnings("unchecked")
	@Override
	public Solution<T>[] apply( Solution<T>... population ){
		String gName = Goal.class.getName();
		Object goal = population[0].data(gName);
    	int mu = population.length;
    	P new_s;
		Solution<T>[] pop = (Solution<T>[])new Solution[ro];
		P[] s_pop = (P[])new Object[ro];
    	Solution<T>[] new_y = (Solution<T>[])new Solution[lambda];
    	for( int k=0; k<lambda; k++ ){
    		int[] subset = select( mu );
            
        	for( int i=0; i<ro; i++ ){
        		pop[i] = population[subset[i]];
        		s_pop[i] = (P)pop[i].info(PARAMETERS_OPERATOR);
        	}
        	new_s = s_mutation.apply(s_recombination.build(s_pop));
        	if( param_mutation != null ){
        		param_mutation.setParameters(new_s);
        	}
        	new_y[k] = mutation.apply(recombination.build(pop));
        	new_y[k].set(PARAMETERS_OPERATOR, new_s);
        	new_y[k].set(gName, goal);
    	}
    	return new_y;
	}
}