/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.math.function.Function;
import unalcol.sort.Order;
import unalcol.object.Tagged;

/**
 *
 * @author jgomez
 */
public abstract class Goal<T, R> extends Function<T,R>{
	public final static String name ="goal"; 
	
	public abstract Order order();
    public abstract R compute( T x );

    public int compare(T x, T y){ return order().compare(apply(x),apply(y)); }
    public int compare(Tagged<T> x, Tagged<T> y){ return order().compare(apply(x),apply(y)); }
    @Override
    public R apply( T x ){
    	R y = compute(x);
        trace(x, y);
    	return y;
    }
}