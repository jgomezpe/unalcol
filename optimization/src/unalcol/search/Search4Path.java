/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.search.space.Space;
import unalcol.search.space.SpaceMoveInfo;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public interface Search4Path<T> extends Search<T> {
    public T apply( Space<T> space, Goal<T> goal, T x );
    public Vector<SpaceMoveInfo<T>> path( Space<T> space, Goal<T> goal, T x );    
}
