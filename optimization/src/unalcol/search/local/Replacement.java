/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.search.solution.Solution;

/**
 *
 * @author jgomez
 */
public interface Replacement<T> {
    public Solution<T> apply( Solution<T> current, Solution<T> next );
    public void init();
}
