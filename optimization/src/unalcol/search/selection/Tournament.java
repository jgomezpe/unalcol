package unalcol.search.selection;

import unalcol.random.integer.IntUniform;
import unalcol.sort.Order;

/**
 * <p>Title: Tournament</p>
 * <p>Description: A tournament selection strategy. In this strategy each individual that
 * is chosen is selected from a group of individuals. The group of individuals are chosen
 * randomly from the population with a uniform probability. From this group of individuals
 * one is chosen using the OptimizationFunction as the probability to win the game.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Tournament<R> implements QualityBasedSelection<R>{
	/**
	 * The tournament size
	 */
	protected int m = 4;

	/**
	 * Selection mechanism used for selecting the tournament winner
	 */
	protected QualityBasedSelection<R> inner = new Elitism<R>(1.0,0.0);

	/**
	 * Constructor: Create a tournament selection strategy with m players.
	 * @param m The number of players in the tournament
	 */
	public Tournament( int m ){
		this.m = m;
	}

	/**
	 * Constructor: Create a tournament selection strategy with m players, using the given
	 * selection strategy for selecting the tournament winner.
	 * @param m The number of players in the tournament
	 * @param s The inner selection strategy for determining the tournament winner
	 */
	public Tournament( int m, QualityBasedSelection<R> s ){
		this.m = m;
		this.inner = s;
	}

	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param g Uniform integer number generator used for picking the candidate solution
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	 */
	protected int choose_one( IntUniform g, R[] x, Order<R> order ){
		@SuppressWarnings("unchecked")
		R[] candidates = (R[])new Object[m];
		int[] indices = new int[m];
		for( int i=0; i<m; i++ ){
			indices[i] = g.next();
			candidates[i] = x[indices[i]];
		}
		return indices[inner.choose_one(candidates,order)];
	}
  
	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param g Uniform integer number generator used for picking the candidate solution
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	 */
	@Override
	public int choose_one( R[] x, Order<R> order ){
		return choose_one( new IntUniform(x.length), x, order );
	}  
  
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param q Quality associated to each candidate solution
	 * @return Indices of the selected candidate solutions
	 */
	@Override
	public int[] apply( int n, R[] x, Order<R> order ){
		IntUniform g =  new IntUniform(x.length);
		int[] sel = new int[n];
		for (int i = 0; i<n; i++) sel[i] = choose_one(g, x, order);
		return sel;
	}
}