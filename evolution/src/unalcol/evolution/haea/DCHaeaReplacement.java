package unalcol.evolution.haea;
import unalcol.optimization.solution.Solution;
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

    public DCHaeaReplacement( HaeaOperators operators, QuasiMetric<T> metric ){
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
    public Vector<Solution<T>> apply( Vector<Solution<T>> parents, Vector<Solution<T>> offspring ){
        int k=0;
        Vector<Solution<T>> buffer = new Vector();
        for( int i=0; i<parents.size(); i++){
            Solution<T> parent = parents.get(0);
            Solution<T> child = offspring.get(k);
            double d = metric.apply(parent.get(), child.get());
            k++;
            for(int h=1; h<operators.getSizeOffspring(i); h++){
                Solution<T> child2 = offspring.get(k);
                double d2 = metric.apply(parent.get(), child.get());
                if( d2 > d ){
                    child = child2;
                    d = d2;
                }
                k++;
            }
            if(parent.value() < child.value()){
                operators.reward(i);
            } else {
                operators.punish(i);
            }
            if(parent.value() < child.value()){
                buffer.add(child);
            }else{
                buffer.add(parent);
            }
        }
        return buffer;
    }    
}
