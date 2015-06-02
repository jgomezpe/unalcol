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
public abstract class Variation<T>{
    public T apply( Space<T> space, T x ){ return space.repair(apply(x)); }
    public abstract T apply( T x );
    public void adapt( double productivity ){}    
}
