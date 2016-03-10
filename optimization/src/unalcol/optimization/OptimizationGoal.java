/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization;

import unalcol.search.RealQualityGoal;
import unalcol.sort.ReversedOrder;
import unalcol.types.real.DoubleOrder;

/**
 *
 * @author jgomez
 */
public class OptimizationGoal<T> extends RealQualityGoal<T> {

    protected OptimizationFunction<T> function;
    
    protected double goal_value;
    
    
    public OptimizationGoal(OptimizationFunction<T> function){
        this( function, true );
    }
    
    public OptimizationGoal( OptimizationFunction<T> function, boolean minimize ){
    	this( function, minimize, minimize?Double.MIN_VALUE:Double.MAX_VALUE );
    }

    public OptimizationGoal( OptimizationFunction<T> function, boolean minimize,
                             double goal_value ){
    	super( goal_value );
        this.function = function;
        this.order = minimize? new ReversedOrder<Double>(new DoubleOrder()):new DoubleOrder();
    }
        
    @Override
    public Double apply(T x) {
    	return function.apply(x);
    }   
    
    @Override
    public boolean nonStationary(){
        return function.isNonStationary();
    }    
}