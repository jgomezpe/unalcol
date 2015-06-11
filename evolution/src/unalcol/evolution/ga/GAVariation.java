package unalcol.evolution.ga;
import unalcol.random.util.*;
import unalcol.search.population.variation.ArityOne;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.population.variation.PopulationVariation;
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
public class GAVariation<T> extends PopulationVariation<T>{
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
    public Vector<T> apply(Vector<T> population) {
        population.shuffle();
        Vector<T> buffer = new Vector<T>();
        int n = xover.arity();
        int m = population.size() / n;
        int k = 0;
        for (int j = 0; j < m; j++) {
            Vector<T> offspring = new Vector<T>();
            for( int i=0; i<n; i++ ){
                offspring.add(population.get(k+i));
            }
            if (generator.next()) {
                offspring = mutation.apply( xover.apply( offspring ) );
            } else {
               for (int i = 0; i < n; i++) {
                    offspring.set(i, (T)Clone.get(offspring.get(i)));
               }
            }
            for( int i=0; i<offspring.size(); i++){
                buffer.add(offspring.get(i));
            }
            k += n;
        }
        return buffer;
    }
}
