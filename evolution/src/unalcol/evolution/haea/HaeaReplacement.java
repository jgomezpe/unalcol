package unalcol.evolution.haea;
import unalcol.search.population.PopulationReplacement;
import unalcol.search.population.PopulationSolution;
import unalcol.types.collection.vector.*;

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
public class HaeaReplacement<T> implements PopulationReplacement<T>{
    /**
     * Set of genetic operators that are used by CEA for evolving the solution chromosomes
     */
    protected HaeaOperators<T> operators = null;

    /**
     * Default constructor
     */
    public HaeaReplacement(HaeaOperators<T> operators){
       this.operators = operators;
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
    public PopulationSolution<T> apply( PopulationSolution<T> current, PopulationSolution<T> next ){
        int k=0;
        Vector<T> buffer = new Vector<T>();
        double[] quality = new double[current.size()];
        for( int i=0; i<current.size(); i++){
            int sel = k; 
            k++;
            for(int h=1; h<operators.getSizeOffspring(i); h++){
                if( next.quality(k) > next.quality(sel) ){
                    sel = k;
                }
                k++;
            }
            if(current.quality(i) < next.quality(sel)){
                operators.reward(i);
            } else {
                operators.punish(i);
            }
            if( current.quality(i) <= next.quality(sel)){
                buffer.add( next.value(sel) );
                quality[i] = next.quality(sel);
            }else{
                buffer.add( current.value(i) );
                quality[i] = current.quality(i);
            }
        }
        return new PopulationSolution<T>(buffer, quality);
    }    
}
