package unalcol.evolution.haea;
import unalcol.search.Goal;
import unalcol.search.RealQualityGoal;
import unalcol.search.population.Population;
import unalcol.search.solution.Solution;
import unalcol.sort.Order;
import unalcol.math.metric.*;

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
	public Population<T> apply( Population<T> current, Population<T> next ){
		String gName = Goal.class.getName();
		RealQualityGoal<T> goal = (RealQualityGoal<T>)current.data(gName);
		Order<Double> order = goal.order();
        Solution<T>[] buffer = new Solution[current.size()];
        int k=0;
        for( int i=0; i<current.size(); i++){
            T parent = current.get(i).object();
            int child = k;
            double d = metric.apply(parent, next.get(child).object());
            k++;
            for(int h=1; h<operators.getSizeOffspring(i); h++){
                double d2 = metric.apply(parent, next.get(k).object());
                if( d2 < d ){
                    child = k;
                    d = d2;
                }
                k++;
            }
            double qp = (Double)current.get(i).info(gName);
            double qc = (Double)next.get(child).info(gName);
            if(order.compare(qp,qc) < 0 ){
                operators.reward(i);
            } else {
                operators.punish(i);
            }
            if(order.compare(qp, qc) <= 0){
                buffer[i] = next.get(child);
            }else{
                buffer[i] = current.get(i);
            }
        }
        return new Population<T>(buffer);
    }    
}