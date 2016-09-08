package unalcol.evolution.haea;
import unalcol.search.population.PopulationReplacement;
import unalcol.sort.Order;
import unalcol.search.Goal;
import unalcol.search.RealQualityGoal;
import unalcol.search.solution.Solution;
import unalcol.search.solution.SolutionManager;
import unalcol.search.population.Population;

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
public class HaeaReplacement<T> implements PopulationReplacement<T>, SolutionManager<T>{
    /**
     * Set of genetic operators that are used by CEA for evolving the solution chromosomes
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
    public Population<T> apply( Population<T> current, Population<T> next ){
    	String gName = Goal.class.getName();
    	@SuppressWarnings("unchecked")
		RealQualityGoal<T> goal = (RealQualityGoal<T>)current.data(gName);
    	//next.set(gName,goal);
    	Order<Double> order = goal.order();
        int k=0;
		Solution<T>[] buffer = (Solution<T>[])tagged_array(current.size());
        for( int i=0; i<current.size(); i++){
            //@TODO: Change the elitism here
            int sel = k; 
            double qs = (Double)next.get(sel).info(gName);
            k++;
            for(int h=1; h<operators.getSizeOffspring(i); h++){
                double qk = (Double)next.get(k).info(gName);
                if( order.compare(qk, qs) > 0 ){
                    sel = k;
                }
                k++;
            }
            double qi = (Double)current.get(i).info(gName);
            if( order.compare(qi, qs) < 0)
                operators.reward(i);
            else
                operators.punish(i);
            
            if( !steady || order.compare(qi, qs) <= 0)
                buffer[i] = next.get(sel);
            else
                buffer[i] = current.get(i);
            
        }
        return new Population<T>(buffer,goal);
    }    
}