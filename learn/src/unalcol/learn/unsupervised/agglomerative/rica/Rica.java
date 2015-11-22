/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.agglomerative.rica;

import unalcol.clone.Clone;
import unalcol.dynamic.DynamicSystem;
import unalcol.dynamic.rain.PickOne;
import unalcol.dynamic.rain.RainMove;
import unalcol.dynamic.rain.RainSystem;
import unalcol.learn.Labeler;
import unalcol.math.logic.Predicate;
import unalcol.types.collection.array.ArrayCollection;

/**
 *
 * @author jgomez
 */
public class Rica<T> implements Labeler<T> {
    protected RainMove<T> move;
    protected PickOne<T> pick;
    protected Predicate<DynamicSystem> stop;
    
    public Rica( RainMove<T> move, Predicate<DynamicSystem> stop, PickOne<T> pick ){
        this.move = move;
        this.pick = pick;
        this.stop = stop;
    }
    
    @Override
    public int[] label(ArrayCollection<T> set) {
        RainMove<T> m = (RainMove<T>)Clone.get(move);
        PickOne<T> p = (PickOne<T>)Clone.get(pick);
        Predicate<DynamicSystem> c = (Predicate<DynamicSystem>)Clone.get(stop);
        RainSystem<T> rain = new RainSystem<>(set,m,p);
        rain.simulate(c);
        return rain.structures();
    }

}
