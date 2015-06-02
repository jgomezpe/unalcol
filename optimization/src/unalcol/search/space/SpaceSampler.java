/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

/**
 *
 * @author jgomez
 */
public interface SpaceSampler<T> {
    public T apply( Space<T> space );
}
