package unalcol.evolution.haea;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.replacement.Replacement;
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
public class HaeaReplacement<T> extends Replacement<T>{
    /**
     * Set of genetic operators that are used by CEA for evolving the solution chromosomes
     */
    protected HaeaOperators operators = null;

    /**
     * Default constructor
     */
    public HaeaReplacement(HaeaOperators operators){
       this.operators = operators;
    }

    /**
     * Adds a subpopulation of parents and associated offsprings to the replacement strategy.
     * These method chooses between parents and offspring in order to define the individuals that
     * will be maintained into the next generation using the HAEA mechanism
     * @param parents Parents
     * @param children
     */
    @Override
    public Vector<Solution<T>> apply( Vector<Solution<T>> parents, Vector<Solution<T>> offspring ){
        int k=0;
        Vector<Solution<T>> buffer = new Vector();
        for( int i=0; i<parents.size(); i++){
            Solution<T> parent = parents.get(i);
            Solution<T> child = offspring.get(k);
            double f = offspring.get(k).value();
            k++;
            for(int h=1; h<operators.getSizeOffspring(i); h++){
                Solution<T> child2 = offspring.get(k);
                double f2 = child2.value();
                if( f2 > f ){
                    child = child2;
                    f = f2;
                }
                k++;
            }
            if(parent.value() < child.value()){
                operators.reward(i);
            } else {
                operators.punish(i);
            }
            if(parent.value() <= child.value()){
                buffer.add(child);
            }else{
                buffer.add(parent);
            }
        }
        return buffer;
    }    
}
