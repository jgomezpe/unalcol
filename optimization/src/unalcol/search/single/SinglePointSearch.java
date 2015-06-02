/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.single;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.Solution;
import unalcol.search.space.Space;

/**
 *
 * @author Jonatan
 */
public abstract class SinglePointSearch<T> implements Search<T> {
    
    public SinglePointSearch(){}
    
    public abstract Solution<T> apply( Solution<T> solution, Space<T> space, Goal<T> goal );
    
    @Override
    public Solution<T> apply(Space<T> space, Goal<T> goal){
        return apply(new Solution<T>(space.get(), goal), space, goal);
    }
}
