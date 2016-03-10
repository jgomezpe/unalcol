package unalcol.search.population;

import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.tracer.Tracer;

public class IterativePopulationSearch<T,R> implements PopulationSearch<T,R> {
    protected Predicate< Population<T> > terminationCondition;
    protected PopulationSearch<T,R> step;
    
    public IterativePopulationSearch( PopulationSearch<T,R> step,
                                      Predicate< Population<T> > tC ){
        terminationCondition = tC;
        this.step = step;
    }

    @Override
    public Population<T> init( Space<T> space, Goal<T,R> goal ){
        terminationCondition.init();
        return step.init(space, goal);
    }
    
    @Override
    public Population<T> apply( Population<T> pop, Space<T> space){
    	Tracer.trace(this, pop, step);
        while( terminationCondition.evaluate(pop) ){
            pop = step.apply(pop, space);
        	Tracer.trace(this, pop, step);
        }
        return pop;
    }

	@Override
	public Solution<T> pick(Population<T> pop) {
		return step.pick(pop);
	}
}