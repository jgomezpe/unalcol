package unalcol.optimization;
import unalcol.search.RealValuedGoal;
import unalcol.sort.Reversed;
import unalcol.sort.Order;

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
public abstract class OptimizationFunction<T> extends RealValuedGoal<T>{
	protected boolean minimize = true;
	protected Order order = null;
	
	public Order order(){
		if( order == null ) order = minimizing()?new Reversed(new unalcol.real.Order()):new unalcol.real.Order();
		return order;
	}

	public boolean minimizing(){ return minimize; }

	public void minimize( boolean minimize ){
		this.minimize = minimize;
		order = null;
	}
	
	/** Updates the optimization function if it is non stationary
     * @param k Current iteration of the optimizer
     */
    public void update( int k ){}
}