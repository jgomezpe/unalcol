package unalcol.evolution.haea;
import unalcol.search.population.PopulationReplacement;
import unalcol.sort.Order;
import unalcol.object.Tagged;
import unalcol.search.BasicGoalBased;
import unalcol.search.RealValuedGoal;

/**
 * <p>Title: HaeaReplacement</p>
 *
 * <p>Description: The HAEA Replacement strategy</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class HaeaReplacement<T> extends BasicGoalBased<T,Double> implements PopulationReplacement<T>{
    /**
     * Set of genetic operators that are used by CEA for evolving the Tagged chromosomes
     */
    protected HaeaOperators<T> operators = null;

    protected boolean steady = true;
    
    /**
     * Default constructor
     */
    public HaeaReplacement(HaeaOperators<T> operators){
       this.operators = operators;
    }
  
    /**
     * Default constructor
     */
    public HaeaReplacement(HaeaOperators<T> operators, boolean steady ){
       this.operators = operators;
       this.steady = steady;
    }
    
    
    public HaeaOperators<T> operators(){ return operators; }

	/**
	 * Adds a subpopulation of parents and associated offsprings to the replacement strategy.
	 * These method chooses between parents and offspring in order to define the individuals that
	 * will be maintained into the next generation using the HAEA mechanism
	 * @param parents Parents
	 * @param children
	 */
	@Override
	public Tagged<T>[] apply( Tagged<T>[] current, Tagged<T>[] next ){
		RealValuedGoal<T> goal = (RealValuedGoal<T>)goal();
		//next.set(gName,goal);
		Order order = goal.order();
        int k=0;
		@SuppressWarnings("unchecked")
		Tagged<T>[] buffer = (Tagged<T>[])new Tagged[current.length];
        for( int i=0; i<current.length; i++){
            try{
            	buffer[i] = current[i];
            //@TODO: Change the elitism here
            	int sel = k; 
            	double qs = goal.apply(next[sel]);
            	k++;
            	for(int h=1; h<operators.getSizeOffspring(i); h++){
            		double qk = goal.apply(next[k]);
            		if( order.compare(qk, qs) > 0 ){
            			sel = k;
            			qs = qk;
            		}
            		k++;
            	}
            	double qi = goal.apply(current[i]);
            	if( order.compare(qi, qs) < 0) operators.reward(i);
            	else operators.punish(i);
            
            	if( !steady || order.compare(qi, qs) <= 0) buffer[i] = next[sel];
            }catch(Exception e){}	
        }
        return buffer;
    }    
}