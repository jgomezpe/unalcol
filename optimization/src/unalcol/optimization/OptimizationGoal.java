/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization;

import unalcol.search.Goal;
import unalcol.tracer.Tracer;

/**
 *
 * @author jgomez
 */
public class OptimizationGoal<T> extends Goal<T> {

    protected boolean minimize = true;
    
    protected OptimizationFunction<T> function;
    
    protected double goal_value = Double.MIN_VALUE;
    
    
    public OptimizationGoal(OptimizationFunction<T> function){
        this.function = function;
    }
    
    public OptimizationGoal( OptimizationFunction<T> function, boolean minimize ){
        this.function = function;
        this.minimize = minimize;
        if( !minimize ) goal_value = Double.MAX_VALUE;
    }

    public OptimizationGoal( OptimizationFunction<T> function, boolean minimize,
                             double goal_value ){
        this.function = function;
        this.minimize = minimize;
        this.goal_value = goal_value;
    }
    
    @Override
    public boolean test(T x) {
        return qTest(apply(x));
    }
    
    @Override
    public boolean qTest(double q) {
        return (goal_value==q);
    }
    
    public double apply(T x){
        double y = function.apply(x);
        Tracer.trace(this, y);
        return y;
    }

    @Override
    public double quality(T x) {
        if( minimize ) return -apply(x);
        else return apply(x);
    }   
    
    @Override
    public boolean nonStationary(){
        return function.isNonStationary();
    }    
}