package unalcol.evolution.ga;
import unalcol.random.util.*;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;
import unalcol.search.solution.variation.SolutionOperator;
import unalcol.search.variation.ArityOneSearchOperator;
import unalcol.search.variation.ArityTwoSearchOperator;
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
public class GAVariation<T> implements SolutionOperator<T>{
	protected Selection<T> selection;
    protected ArityOneSearchOperator<T> mutation;
    protected ArityTwoSearchOperator<T> xover;
    protected RandBool generator;

    public GAVariation( Selection<T> selection, ArityOneSearchOperator<T> mutation,
    					ArityTwoSearchOperator<T> xover, double probability) {
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
                    offspring[i] = (Solution<T>)parents[i].clone(parents[i].object());
            }
            for( int i=0; i<offspring.length; i++){
                buffer.add(offspring[i]);
            }
        }
        return buffer.toArray();
	}
}