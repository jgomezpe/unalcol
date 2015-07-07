/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.single;

import unalcol.search.Solution;
import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.space.Space;
import unalcol.tracer.Tracer;

/**
 *
 * @author jgomez
 */
public class IterativeSinglePointSearch<T> extends SinglePointSearch<T> {
    protected Predicate< Solution<T> > terminationCondition;
    protected SinglePointSearch<T> step;
    
    public IterativeSinglePointSearch( SinglePointSearch<T> step,
                                       Predicate< Solution<T> > tC ){
        terminationCondition = tC;
        this.step = step;
    }

    @Override
    public void init(){
        terminationCondition.init();        
    }
    
    public Solution<T> step(Solution<T> x, Space<T> space, Goal<T> goal){
        return step.apply(x, space, goal);
    }    
    
	@Override
	public Solution<T> apply(Solution<T> solution, Space<T> space, Goal<T> goal) {
        init();
        int i=0;
        Tracer.trace(this, i, solution);
        while( terminationCondition.evaluate(solution) ){
            solution = step(solution, space, goal);
            i++;
            Tracer.trace(this, i, solution);
        }
        return solution;
	}        
}