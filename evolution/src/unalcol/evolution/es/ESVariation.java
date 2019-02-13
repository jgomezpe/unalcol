package unalcol.evolution.es;

import unalcol.integer.Uniform;
import unalcol.search.variation.ParameterizedObject;
import unalcol.search.variation.Variation;
import unalcol.search.variation.Variation_1_1;
import unalcol.search.variation.Variation_n_1;
import unalcol.object.Tagged;

public class ESVariation<T,P> implements Variation<T>{ 
	public static final String PARAMETERS_OPERATOR = "Parameters";
	protected int lambda;
	protected int ro;
	protected Variation_n_1<T> recombination;
	protected Variation_1_1<T> mutation;
	protected ParameterizedObject<P> param_mutation;
	protected Variation_n_1<P> s_recombination;
	protected Variation_1_1<P> s_mutation;
	
	@SuppressWarnings("unchecked")
	public ESVariation( int lambda, int ro, 
						Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
						Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation) {
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
    	Uniform g = new Uniform(mu);
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
	public Tagged<T>[] apply( Tagged<T>... population ){
    	int mu = population.length;
    	P new_s;
		Tagged<T>[] pop = (Tagged<T>[])new Tagged[ro];
		P[] s_pop = (P[])new Object[ro];
    	Tagged<T>[] new_y = (Tagged<T>[])new Tagged[lambda];
    	for( int k=0; k<lambda; k++ ){
    		int[] subset = select( mu );
            
        	for( int i=0; i<ro; i++ ){
        		pop[i] = population[subset[i]];
        		s_pop[i] = (P)pop[i].getTag(PARAMETERS_OPERATOR);
        	}
        	new_s = s_mutation.apply(s_recombination.apply(s_pop)[0]);
        	if( param_mutation != null ){
        		param_mutation.setParameters(new_s);
        	}
        	new_y[k] = mutation.apply(recombination.apply(pop)[0]);
        	new_y[k].setTag(PARAMETERS_OPERATOR, new_s);
    	}
    	return new_y;
	}
}