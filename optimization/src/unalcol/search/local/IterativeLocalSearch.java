/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.math.logic.Predicate;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.tracer.Tracer;

/**
 *
 * @author jgomez
 */
public class IterativeLocalSearch<T,R> extends LocalSearch<T,R> {
    protected Predicate< Solution<T> > terminationCondition;
    protected LocalSearch<T,R> step;
    
    public IterativeLocalSearch( LocalSearch<T,R> step,
                                 Predicate< Solution<T> > tC ){
        terminationCondition = tC;
        this.step = step;
    }

    public Solution<T> step(Solution<T> x, Space<T> space){
        return step.apply(x, space);
    }    
    
	@Override
	public Solution<T> apply(Solution<T> solution, Space<T> space) {
        terminationCondition.init();
        int i=0;
        Tracer.trace(this, i, solution);
        while( terminationCondition.evaluate(solution) ){
            solution = step(solution, space);
            i++;
            Tracer.trace(this, i, solution);
        }
        return solution;
	}
	
	@Override
	public void init(){
		terminationCondition.init();
		step.init();
	}
}