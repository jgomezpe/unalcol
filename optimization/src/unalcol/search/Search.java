/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.search.space.Space;

/**
 *
 * @author jgomez
 */
public interface Search<T> {
    public Solution<T> apply( Space<T> space, Goal<T> goal );
    public void init();
}
