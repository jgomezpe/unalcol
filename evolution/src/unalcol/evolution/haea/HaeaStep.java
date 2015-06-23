package unalcol.evolution.haea;

import unalcol.search.Goal;
import unalcol.search.population.PopulationSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.variation.PopulationVariation;
import unalcol.search.selection.Selection;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.*;

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
public class HaeaStep<T> extends PopulationSearch<T>{

	protected HaeaReplacement<T> replacement;
	
    /**
     * Set of genetic operators that are used by CEA for evolving the solution chromosomes
     */
    protected HaeaOperators<T> operators = null;

    /**
     * Extra parent selection mechanism
     */
    protected Selection<T> selection;

    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStep(int n, HaeaReplacement<T> replacement, Selection<T> selection) {
    	super(n);
        //System.out.println("Haea strategy");
    	this.replacement = replacement;
        this.operators = replacement.operators();
        this.selection = selection;
    }

    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStep(int n, HaeaOperators<T> operators, Selection<T> selection) {
    	super(n);
        //System.out.println("Haea strategy");
    	this.replacement = new HaeaReplacement<T>( operators );
        this.operators = operators;
        this.selection = selection;
    }

    /**
     * Gets a subpopulation that can be used for selecting a second parent
     * @param id First parent
     * @param population Full Population
     * @return A subpopulation that can be used for selecting a second parent
     */
    public Vector<Integer> select( int id, PopulationSolution<T> population ){
        return null;
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
	@Override
	public PopulationSolution<T> apply( PopulationSolution<T> population, Space<T> space, Goal<T> goal ){
    	operators.resize(population.size());
        Vector<T> buffer = new Vector<T>();
        Vector<Double> next_q = new Vector<Double>();
        for (int i = 0; i < population.size(); i++) {
            // next line added  Feb 25, 2011
            if( available(i) ){
                int oper = operators.select(i);
                PopulationVariation<T> o = operators.get(i, oper);
                Vector<Integer> subset = select(i, population);
                Vector<T> pop;
                double[] quality;
                if(subset==null){
                	pop = population.value();
                	quality = population.quality();
                }else{
                	pop = new Vector<T>();
                	quality = new double[subset.size()];
                	int id=0;
                	for( int k : subset ){
                		pop.add(population.value(k));
                		quality[id] = population.quality(k);
                		id++;
                	}
                }
                Vector<T> parent = new Vector<T>();
                parent.add(population.value(i));
                pop = selection.apply(o.arity()-1, pop, quality);
                for( int k=0; k<pop.size(); k++ ){
                    parent.add( pop.get(k) );
                }
                System.out.println("Arity.."+o.arity());
                Vector<T> offspring = o.apply(space,parent);
                operators.setSizeOffspring(i, offspring.size());
                for( T x : offspring ){                	
                    buffer.add(x);
                    next_q.add(goal.quality(x));
                }
            }else{
                operators.unselect(i);
                operators.setSizeOffspring(i, 1);
                buffer.add(population.value(i));
                next_q.add(population.quality(i));
            }
        }
        return replacement.apply(population, new PopulationSolution<T>(buffer, next_q));
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub	
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
