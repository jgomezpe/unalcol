package unalcol.evolution.haea;

import unalcol.optimization.solution.Solution;
import unalcol.optimization.selection.Selection;
import unalcol.optimization.operators.Operator;
import unalcol.types.collection.vector.*;
import unalcol.evolution.*;

/**
 * <p>Title: HAEA</p>
 * <p>Description: The Hybrid Adaptive Evolutionary Algorithm Offspring Generation strategy as 
 * proposed by Gomez in "Self Adaptation of Operator Rates in Evolutionary Algorithms",
 * Proceedings of Gecco 2004.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class HaeaStrategy<G,P> extends OffspringGeneration<G,P>{

    /**
     * Set of genetic operators that are used by CEA for evolving the solution chromosomes
     */
    protected HaeaOperators<G> operators = null;

    /**
     * Extra parent selection mechanism
     */
    protected Selection<P> selection;

    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStrategy(HaeaOperators<G> operators, GrowingFunction<G, P> grow,
            Selection<P> selection) {
        super( grow );
        //System.out.println("Haea strategy");
        this.operators = operators;
        this.selection = selection;
    }

    /**
     * Gets a subpopulation that can be used for selecting a second parent
     * @param id First parent
     * @param population Full Population
     * @return A subpopulation that can be used for selecting a second parent
     */
    public Vector<Solution<P>> select( int id, Vector<Solution<P>> population ){
        return population;
    }

    /**
     * Determines if the individual can be selected as firts parent
     * @param id Individuals's id
     * @return <i>true</i> if the individual can be selected as first parent, <i>false</i> otherwise
     */
    public boolean available( int id ){
        return true;
    }


    /**
     * Generates a population of offspring individuals following haea rules.
     * @param population The population to be transformed
     * @param replace Replacement mechanism
     * @param f Function to be optimized
     */
    public Vector<Solution<P>> apply(Vector<Solution<P>> population) {
        operators.resize(population.size());
        Vector<Solution<P>> buffer = new Vector();
        for (int i = 0; i < population.size(); i++) {
            // next line added  Feb 25, 2011
            if( available(i) ){
                int oper = operators.select(i);
                Operator<G> o = operators.get(i, oper);
                Vector<G> parent_genomes = new Vector();
                parent_genomes.add(((Individual<G, P>) population.get(i)).genome());
                Vector<Solution<P>> pop = selection.apply(o.getArity()-1, select(i, population));
                for( int k=0; k<pop.size(); k++ ){
                    parent_genomes.add( ((Individual<G,P>)pop.get(k)).genome() );
                }
                Vector<Solution<P>> offspring = grow.build(o.apply(parent_genomes));
                operators.setSizeOffspring(i, offspring.size());
                for( int k=0; k<offspring.size(); k++){
                    buffer.add(offspring.get(k));
                }
            }else{
                operators.unselect(i);
                operators.setSizeOffspring(i, 1);
                buffer.add(population.get(i));
            }
        }
        return buffer;
    }

    /**
     * Return the statistical information of the population given. includes information
     * of the transformation process if any
     * @param population Population used to calculate the statistical information
     * @return Statistical information of the transformation process
     */
    /*public double[] descriptors( Population population ){
        operators.setPopulationSize(population.size());
        return super.descriptors( population );
    }*/
    
}
