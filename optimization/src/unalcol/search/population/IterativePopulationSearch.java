package unalcol.search.population;

import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.space.Space;

public class IterativePopulationSearch<T> extends PopulationSearch<T> {
    protected Predicate< PopulationSolution<T> > terminationCondition;
    protected PopulationSearch<T> step;
    
    public IterativePopulationSearch( int n, PopulationSearch<T> step,
                                      Predicate< PopulationSolution<T> > tC ){
    	super( n );
        terminationCondition = tC;
        this.step = step;
    }

    public void init(){
        terminationCondition.init();        
    }
    
    @Override
    public Solution<T> apply( Space<T> space, Goal<T> goal){
        init();
    	PopulationSolution<T> x = new PopulationSolution<T>(space.get(n), goal);
        while( terminationCondition.evaluate(x) ){
            x = apply(x, space, goal);
        }
        return x.pick();
    }

	@Override
	public PopulationSolution<T> apply(PopulationSolution<T> pop,
			Space<T> space, Goal<T> goal) {
		return step.apply(pop, space, goal);
	}        

}
