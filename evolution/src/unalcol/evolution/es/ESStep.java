package unalcol.evolution.es;

import unalcol.search.Goal;
import unalcol.search.population.PopulationReplacement;
import unalcol.search.population.PopulationSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.variation.BuildOne;
import unalcol.search.space.ArityOne;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;

public class ESStep<T,P> extends PopulationSearch<T> {
	protected int lambda;
	protected int ro;
	protected BuildOne<T> recombination;
	protected ArityOne<T> mutation;
	protected BuildOne<P> s_recombination;
	protected PopulationReplacement<T> replacement;
	protected Space<P> s_space;
	protected Vector<P> s;
	
	public ESStep( int n, int lambda, int ro, 
			       BuildOne<T> y_recombination, ArityOne<T> mutation, 
			       BuildOne<P> s_recombination, ArityOne<P> s_mutation, Space<P> s_space,
			       PopulationReplacement<T> replacement) {
		super(n);
		this.lambda = lambda;
		this.ro = ro;
		this.s = new Vector<>();
		this.recombination = y_recombination;
		this.mutation = mutation;
		this.s_recombination = s_recombination;
		this.s_space = s_space;
		this.replacement = replacement;
	}

    /**
     * Gets a subpopulation of lambda individuals that can be used for the marriage process
     * @param population Full Population
     * @return A subpopulation of lambda individuals that can be used for the marriage process
     */
    public Vector<Integer> select( PopulationSolution<T> population ){
    	
        return null;
    }

    protected void adjust_s( int miu ){
		while( s.size() < miu ){
			s.add(s_space.get());
		}
		while( s.size() > miu ){
			s.remove(s.size()-1);
		}
    }
	
    /**
     * Generates a population of offspring individuals following haea rules.
     * @param population The population to be transformed
     * @param replace Replacement mechanism
     * @param f Function to be optimized
     */
	@Override
	public PopulationSolution<T> apply( PopulationSolution<T> population, Space<T> space, Goal<T> goal ){
    	int miu = population.size();
    	adjust_s(miu);
    	Vector<P> new_s = new Vector<>();
    	Vector<T> new_y = new Vector<>();
    	double[] quality = new double[lambda];
    	for( int l=0; l<lambda; l++ ){
    		Vector<Integer> subset = select( population );
            @SuppressWarnings("unchecked")
			T[] pop = (T[])new Object[subset.size()];
			@SuppressWarnings("unchecked")
			P[] s_pop = (P[])new Object[subset.size()];
            
        	for( int i=0; i<subset.size(); i++ ){
        		pop[i] = population.value(subset.get(i));
        		s_pop[i] = s.get(subset.get(i));
        	}
        	new_s.add( s_recombination.build(s_pop) );
        	T child = recombination.build(pop);
        	new_y.add( child );
        	quality[l] = goal.quality(child);
    	}
    	return population;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}	
	
}
