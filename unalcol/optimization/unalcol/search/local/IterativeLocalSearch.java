/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.space.Space;
import unalcol.services.Service;
import unalcol.tracer.Count;
import unalcol.tracer.Tracer;
import unalcol.object.Tagged;

/**
 *
 * @author jgomez
 */
public class IterativeLocalSearch<T,R> extends LocalSearch<T,R> {
    protected Predicate< Tagged<T> > terminationCondition;
    protected LocalSearch<T,R> step;
	protected Tracer t = new Count();
    
    public IterativeLocalSearch( LocalSearch<T,R> step,
                                 Predicate< Tagged<T> > tC ){
        terminationCondition = tC;
        this.step = step;
    }
    
	@Override
    public void setGoal(Goal<T,R> goal){ 
		step.setGoal(goal); 
		t.start();
		Service.register(t, goal);		
	}
        
	@Override 
	public Goal<T,R> goal(){ return step.goal(); }
    
    public Tagged<T> step(Tagged<T> x, Space<T> space){
        return step.apply(x, space);
    }    
    
	@Override
	public Tagged<T> apply(Tagged<T> x, Space<T> space) {
        terminationCondition.init();
		t.start();
		Service.register(t, this.goal());		
        trace(t.get(), x);
        while( terminationCondition.evaluate(x) ){
            x = step(x, space);
            trace(t.get(), x);
        }
        return x;
	}
	
	@Override
	public void init(){
		terminationCondition.init();
		step.init();
	}
}