package unalcol.evolution.haea;

import unalcol.search.Goal;
import unalcol.search.GoalBased;
import unalcol.search.RealValuedGoal;
import unalcol.search.population.RealValuedPopulationGoal;
import unalcol.search.population.VariationReplacePopulationSearch;
import unalcol.search.selection.Selection;
import unalcol.search.space.Space;
import unalcol.object.Tagged;

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
public class HaeaStep<T> extends VariationReplacePopulationSearch<T,Double>{
    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStep(int mu, Selection<T> parent_selection, HaeaReplacement<T> replacement) {
    	super( mu, new HaeaVariation<T>(parent_selection, replacement.operators()), replacement);
    }

    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStep(int mu, Selection<T> parent_selection, HaeaOperators<T> operators) {
    	super(mu, new HaeaVariation<T>(parent_selection, operators ),
    			 new HaeaReplacement<T>( operators ) );
    }

    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStep(int mu, Selection<T> parent_selection, HaeaOperators<T> operators, boolean steady) {
    	super(mu, new HaeaVariation<T>(parent_selection, operators ),
    			 new HaeaReplacement<T>( operators, steady ) );
    }

    /**
     * Constructor: Creates a Haea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public HaeaStep(int mu, HaeaVariation<T> variation, HaeaReplacement<T> replacement ){
    	super( mu, variation, replacement);
    }

	
	public HaeaOperators<T> operators(){
		return ((HaeaVariation<T>)variation).operators();
	}
	
	@SuppressWarnings("unchecked")
	public void setGoal(Goal<T,Double> goal){
		((GoalBased<T,Double>)replace).setGoal(goal);
	}

	@SuppressWarnings("unchecked")
	public Goal<T,Double> goal(){
		return ((GoalBased<T,Double>)replace).goal();
	}
	
	@Override
	public Tagged<T>[] init(Space<T> space){
		Tagged<T>[] pop = super.init(space);
		((HaeaVariation<T>)this.variation).operators().resize(pop.length);
		return pop;
	}
	
	@Override
	public Tagged<T> pick(Tagged<T>[] pop) {
		RealValuedPopulationGoal<T> g = new RealValuedPopulationGoal<T>();
		return g.pick(pop, (RealValuedGoal<T>)goal());
	}	
}