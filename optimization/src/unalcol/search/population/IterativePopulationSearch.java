package unalcol.search.population;

import unalcol.descriptors.Descriptors;
import unalcol.io.Write;
import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.tracer.Tracer;

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
    	System.out.println("#####");
        init();
    	PopulationSolution<T> x = new PopulationSolution<T>(space.get(n), goal);
    	Tracer.trace(this, x, step);
        while( terminationCondition.evaluate(x) ){
            x = apply(x, space, goal);
        	Tracer.trace(this, x, step);
        }
        return x.pick();
    }

	@Override
	public PopulationSolution<T> apply(PopulationSolution<T> pop,
			Space<T> space, Goal<T> goal) {
		return step.apply(pop, space, goal);
	}        

}
