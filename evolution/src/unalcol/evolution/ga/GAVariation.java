package unalcol.evolution.ga;
import unalcol.random.util.*;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;
import unalcol.search.variation.Variation;
import unalcol.search.variation.Variation_1_1;
import unalcol.search.variation.Variation_2_2;
import unalcol.types.collection.vector.Vector;

/**
 * <p>Title: ClassicStrategy</p>
 *
 * <p>Description: The classic genetic algorithm offspring generation strategy</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GAVariation<T> extends Variation<T>{
	protected Selection<T> selection;
    protected Variation_1_1<T> mutation;
    protected Variation_2_2<T> xover;
    protected RandBool generator;

    public GAVariation( Selection<T> selection, Variation_1_1<T> mutation,
    		Variation_2_2<T> xover, double probability) {
    	this.selection = selection;
        this.xover = xover;
        this.mutation = mutation;
        generator = new RandBool( 1.0 - probability );
    }

    
	@SuppressWarnings("unchecked")
	@Override
	public Solution<T>[] apply(Solution<T>... pop) {
		Shuffle<Solution<T>> shuffle = new Shuffle<Solution<T>>();
		shuffle.apply(pop);
		pop = selection.pick(pop.length, pop);
        Vector<Solution<T>> buffer = new Vector<Solution<T>>();
        int n = xover.arity();
        int m = pop.length / n;
        int k = 0;
        Solution<T>[] parents = (Solution<T>[])new Solution[n];
        for (int j = 0; j < m; j++) {
            for( int i=0; i<n; i++ ){
                parents[i] = pop[k];
                k++;
            }
            Solution<T>[] offspring;
            if (generator.next()) {
            	offspring = mutation.apply(xover.apply(parents));
            } else {
            	offspring = (Solution<T>[])(new Solution[n]);
            	for (int i = 0; i < n; i++) 
                    offspring[i] = parents[i];
            }
            for( int i=0; i<offspring.length; i++){
                buffer.add(offspring[i]);
            }
        }
        return buffer.toArray();
	}
}