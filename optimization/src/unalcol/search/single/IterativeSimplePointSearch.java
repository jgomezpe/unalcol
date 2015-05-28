/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.single;

import unalcol.search.Solution;
import unalcol.math.logic.Predicate;
import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.space.Space;

/**
 *
 * @author jgomez
 */
public class IterativeSimplePointSearch<T> implements Search<T> {
    protected Predicate< Solution<T> > terminationCondition;
    protected SimplePointSearch<T> step;
    
    public IterativeSimplePointSearch( SimplePointSearch<T> step,
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
    public Solution<T> apply( Space<T> space, Goal<T> goal){
        init();
        Solution<T> x = step.apply(space, goal);
        while( !terminationCondition.evaluate(x) ){
            x = step(x, space, goal);
        }
        return x;
    }        
}