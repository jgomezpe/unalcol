/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.single;

import unalcol.search.Solution;
import unalcol.search.Goal;
import unalcol.search.space.Space;

/**
 *
 * @author jgomez
 */
public abstract class SearchStep<T> {
    public abstract Solution<T> apply(Solution<T> x, Space<T> space, Goal<T> goal);        
    public Solution<T> apply(T x, Space<T> space, Goal<T> goal){
        return apply( new Solution<T>(x, goal.quality(x)), space, goal );
    }
    public abstract void init();    
}
