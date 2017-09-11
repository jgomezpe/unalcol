package unalcol.search.selection;

import unalcol.random.integer.IntRoulette;
import unalcol.search.Goal;
import unalcol.sort.Order;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.keymap.ValueOrder;
import unalcol.types.collection.vector.*;
import unalcol.sort.ReversedOrder;

/**
 * <p>Title: Elitism</p>
 * <p>Description: A elitist selection strategy. In this strategy the best individuals
 * (Elite percentage) are always selected and the worst individuals (cull percentage)
 * are never taken into account. The remaining part of the individual is chosen
 * randomly, and each individual has a probability to be chosen that is proportional to
 * its OptimizationFunction.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Elitism<T,R> extends GoalBasedSelection<T,R>{

	/**
	 * Elite percentage: Percentage of individuals to be included in the selection
	 * according to their OptimizationFunction
	 */
	protected double elite_percentage = 0.1;

	/**
	 * Cull percentage: percentage of individuals to be excluded in the selection
	 * according to their OptimizationFunction
	 */
	protected double cull_percentage = 0.1;
	

	/**
	 * Constructor: Create a Elitist selection strategy.
	 * @param _elite_percentage Percentage of individuals to be included in the selection
	 * @param _cull_percentage Percentage of individuals to be excluded in the selection
	 */
	public Elitism( double _elite_percentage, double _cull_percentage ){
		elite_percentage = _elite_percentage;
		cull_percentage = _cull_percentage;
	}
  
	/**
	 * Constructor: Create a Elitist selection strategy.
	 * @param _elite_percentage Percentage of individuals to be included in the selection
	 * @param _cull_percentage Percentage of individuals to be excluded in the selection
	 */
	public Elitism( Goal<T,R> goal, double _elite_percentage, double _cull_percentage ){
		super(goal);
		elite_percentage = _elite_percentage;
		cull_percentage = _cull_percentage;
	}
  
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param q Quality associated to each candidate solution
	 * @return Indices of the selected candidate solutions
	 */
	@Override
	public int[] apply( int n, R[] x ){
		Order<R> order = goal().order();
		int[] sel = new int[n];
		int s = x.length;
		SortedVector<KeyValue<Integer,R>> indexq = new SortedVector<KeyValue<Integer,R>>( 
			new ReversedOrder<KeyValue<Integer,R>>( new ValueOrder<Integer,R>(order) ) );
		for( int i=0; i<s; i++ ) indexq.add(new KeyValue<Integer,R>(i, x[i] ) );
      
		int m = (int) (s * elite_percentage);
		for (int i = 0; i < n && i < m; i++) sel[i]=indexq.get(i).key();
      
		if( m<n ){
			int k = (int) (s * (1.0 - cull_percentage));
			IntRoulette generator = new IntRoulette(k);
			n -= m;
			int[] index = generator.generate(n);
			for (int i=0; i<n; i++){
				sel[m] = indexq.get(index[i]).key();
				m++;
			}
		}
		return sel;
	}
  

	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	 */
	@Override
	public int choose_one( R[] x ){
		Order<R> order = goal().order();
		int k = 0;
		R q = x[k];
		for( int i=1; i<x.length; i++ ){
			R q2 = x[i];
			if( order.compare(q, q2) < 0 ){
				k = i;
				q = q2;
			}
		}
		return k;
	}
}