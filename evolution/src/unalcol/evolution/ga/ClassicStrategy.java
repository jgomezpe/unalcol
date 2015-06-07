package unalcol.evolution.ga;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.selection.Selection;
import unalcol.optimization.operators.ArityOne;
import unalcol.optimization.operators.RefiningOperator;
import unalcol.optimization.operators.ArityTwo;
import unalcol.evolution.*;
import unalcol.random.util.*;
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
public class ClassicStrategy<G> extends OffspringGeneration<G,P>{
    protected Selection<P> parent_selection;
    protected RefiningOperator operator;
    protected BooleanGenerator generator;
    public ClassicStrategy( Selection<P> parent_sel, GrowingFunction<G,P> grow, 
            ArityOne<G> mutation, ArityTwo<G> xover, double probability) {
        this( parent_sel, grow, new RefiningOperator(xover, mutation), probability );
    }

    public ClassicStrategy( Selection<P> parent_sel, GrowingFunction<G,P> grow,
                            RefiningOperator<G> operator, double probability) {
        super( grow );
        parent_selection = parent_sel;
        this.operator = operator;
        generator = new BooleanGenerator( 1.0 - probability );
    }

    @Override
    public Vector<Solution<P>> apply(Vector<Solution<P>> population) {
        population = parent_selection.apply(population.size(), population);
        population.shuffle();
        Vector buffer = new Vector();
        int n = operator.getArity();
        int m = population.size() / n;
        int k = 0;
        for (int j = 0; j < m; j++) {
            Vector<Solution<P>> offspring = new Vector();
            for( int i=0; i<n; i++ ){
                offspring.add(population.get(k+i));
            }
            if (generator.next()) {
                offspring = grow.apply( operator, offspring );
            } else {
                try{
                   for (int i = 0; i < n; i++) {
                        offspring.set(i, (Solution<P>)Clone.get(offspring.get(i)));
                   }
                }catch( Exception e ){}
            }
            for( int i=0; i<offspring.size(); i++){
                buffer.add(offspring.get(i));
            }
            k += n;
        }
        return buffer;
    }
}
