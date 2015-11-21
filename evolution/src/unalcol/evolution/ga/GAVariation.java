package unalcol.evolution.ga;
import unalcol.random.util.*;
import unalcol.search.space.ArityOne;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.population.variation.Operator;
import unalcol.types.collection.vector.*;
import unalcol.clone.*;

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
public class GAVariation<T> extends Operator<T>{
    protected ArityOne<T> mutation;
    protected ArityTwo<T> xover;
    protected RandBool generator;
    public GAVariation( ArityOne<T> mutation, ArityTwo<T> xover, double probability) {
        this.xover = xover;
        this.mutation = mutation;
        generator = new RandBool( 1.0 - probability );
    }

	@SuppressWarnings("unchecked")
	@Override
	public Vector<T> apply(T... pop) {
		Shuffle<T> shuffle = new Shuffle<T>();
		shuffle.apply(pop);
        Vector<T> buffer = new Vector<T>();
        int n = xover.arity();
        int m = pop.length / n;
        int k = 0;
        T[] parents = (T[])new Object[n];
        for (int j = 0; j < m; j++) {
            for( int i=0; i<n; i++ ){
                parents[i] = pop[k];
                k++;
            }
            Vector<T> offspring = new Vector<T>();
            if (generator.next()) {
            	offspring = mutation.apply(xover.apply(parents));
            	//offspring = xover.apply( parents );
            	//for( int i=0; i<n; i++){
            	//	offspring.set( i, mutation.apply( offspring.get(i) ) );
            	//}                
            } else {
               for (int i = 0; i < n; i++) {
                    offspring.add((T)Clone.create(parents[i]));
               }
            }
            for( int i=0; i<offspring.size(); i++){
                buffer.add(offspring.get(i));
            }
        }
        return buffer;
	}
}
