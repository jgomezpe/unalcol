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
public class Space<T> {
    public boolean feasible( T x ){ return true; }
    
    public double feasibility( T x ){ return 1.0; }
}
