/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.math.function.Function;
import unalcol.sort.Order;
import unalcol.types.object.tagged.Tagged;

/**
 *
 * @author jgomez
 */
public interface Goal<T, R> extends Function<T,R>{
	public final static String name ="goal"; 
	public Order order();
    public default int compare(T x, T y){ return order().compare(apply(x),apply(y)); }
    public default int compare(Tagged<T> x, Tagged<T> y){ return order().compare(apply(x),apply(y)); }
    public R compute( T x );
    @Override
    public default R apply( T x ){
    	R y = compute(x);
        trace(x, y);
    	return y;
    }
}