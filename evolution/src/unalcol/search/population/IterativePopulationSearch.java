package unalcol.search.population;

import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.space.Space;
import unalcol.types.object.Tagged;

public class IterativePopulationSearch<T,R> implements PopulationSearch<T,R> {
    protected Predicate<Tagged<T>[] > terminationCondition;
    protected PopulationSearch<T,R> step;
    
    public IterativePopulationSearch( PopulationSearch<T,R> step,
                                      Predicate< Tagged<T>[] > tC ){
        terminationCondition = tC;
        this.step = step;
    }

    @Override
    public Tagged<T>[] init( Space<T> space ){
        terminationCondition.init();
        return step.init(space);
    }
    
	@Override
    public void setGoal(Goal<T,R> goal){ step.setGoal(goal); }
        
	@Override 
	public Goal<T,R> goal(){ return step.goal(); }
        
	@Override
	public Tagged<T>[] apply( Tagged<T>[] pop, Space<T> space){
		trace(pop, step);
		while( terminationCondition.evaluate(pop) ){
			pop = step.apply(pop, space);
        	trace(pop, step);
		}
    		return pop;
	}

	@Override
	public Tagged<T> pick(Tagged<T>[] pop) {
		return step.pick(pop);
	}
	
	@Override
	public void init(){
		terminationCondition.init();
		step.init();
	}
}