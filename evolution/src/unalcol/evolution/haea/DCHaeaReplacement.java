package unalcol.evolution.haea;
import unalcol.search.population.PopulationSolution;
import unalcol.clone.Clone;
import unalcol.math.metric.*;
import unalcol.types.collection.vector.Vector;

/**
 * <p>Title: DCHaeaReplacement</p>
 * <p>Description: The Deterministic Crowding Replacement Strategy for the HAEA Algorithm.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class DCHaeaReplacement<T> extends HaeaReplacement<T>{
    protected QuasiMetric<T> metric;

    public DCHaeaReplacement( HaeaOperators<T> operators, QuasiMetric<T> metric ){
        super( operators );
        this.metric = metric;
    }

    /**
     * Adds a subpopulation of parents and associated offsprings to the replacement strategy.
     * These method chooses between parents and offspring in order to define the individuals that
     * will be maintained into the next generation using the HAEA mechanism
     * @param parents Parents
     * @param children
     */
    @SuppressWarnings("unchecked")
	public PopulationSolution<T> apply( PopulationSolution<T> current, PopulationSolution<T> next ){
        Vector<T> buffer = new Vector<T>();
        double[] q = new double[current.size()];
        int k=0;
        for( int i=0; i<current.size(); i++){
            T parent = current.value(i);
            int child = k;
            double d = metric.apply(parent, next.value(child));
            k++;
            for(int h=1; h<operators.getSizeOffspring(i); h++){
                double d2 = metric.apply(parent, next.value(k));
                if( d2 < d ){
                    child = k;
                    d = d2;
                }
                k++;
            }
            if(current.quality(i) < next.quality(child)){
                operators.reward(i);
            } else {
                operators.punish(i);
            }
            if(current.quality(i) <= next.quality(child)){
                buffer.add(next.value(child));
                q[i] = next.quality(child);
            }else{
                buffer.add((T)Clone.create(parent));
                q[i] = current.quality(i);
            }
        }
        return new PopulationSolution<T>(buffer, q);
    }    
}
