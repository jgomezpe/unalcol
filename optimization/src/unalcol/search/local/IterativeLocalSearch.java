/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.math.logic.Predicate;
import unalcol.Tagged;
import unalcol.Thing;
import unalcol.search.Goal;
import unalcol.search.space.Space;
import unalcol.services.Service;
import unalcol.tracer.Tracer;

/**
 *
 * @author jgomez
 */
public class IterativeLocalSearch<T,R> extends Thing implements LocalSearch<T,R> {
    protected Predicate< Tagged<T> > terminationCondition;
    protected LocalSearch<T,R> step;
    
    public IterativeLocalSearch( LocalSearch<T,R> step,
                                 Predicate< Tagged<T> > tC ){
        terminationCondition = tC;
        this.step = step;
    }
    
	@Override
    public void setGoal(Goal<T,R> goal){ step.setGoal(goal); }
        
	@Override 
	public Goal<T,R> goal(){ return step.goal(); }
    
    public Tagged<T> step(Tagged<T> x, Space<T> space){
        return step.apply(x, space);
    }    
    
	@Override
	public Tagged<T> apply(Tagged<T> Tagged, Space<T> space) {
        terminationCondition.init();
        int i=0;
        try{ Service.run(Tracer.name,this, i, Tagged); }catch(Exception e){}
        while( terminationCondition.evaluate(Tagged) ){
            Tagged = step(Tagged, space);
            i++;
            try{ Service.run(Tracer.name,this, i, Tagged); }catch(Exception e){}
        }
        return Tagged;
	}
	
	@Override
	public void init(){
		terminationCondition.init();
		step.init();
	}
}