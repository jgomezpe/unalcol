package unalcol.optimization;
import unalcol.algorithm.Algorithm;

/**
 * <p>Title: OptimizationFunction</p>
 *
 * <p>Description: Abstract definition of an optimization function. An optimization function
 * is a map f:T -> R  where T is any set and R is the real numbers set.</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class OptimizationFunction<T> extends Algorithm<T,Double>{
    /**
     * Determines if the fitness function is stationary or not, i.e.,
     * if the value of the function for a given value can change in time (non-stationary) 
     * or not (stationary)
     * @return true if the fitness function is not stationary, false if it is stationary
     */
    public boolean isNonStationary() { return false; }

    /** Updates the optimization function if it is non stationary
     * @param k Current iteration of the optimizer
     */
    public void update( int k ){}
}