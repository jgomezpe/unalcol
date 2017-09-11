package unalcol.search.selection;

import unalcol.random.integer.IntUniform;
import unalcol.search.Goal;

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
public class Tournament<T,R> extends GoalBasedSelection<T,R>{
	/**
	 * The tournament size
	 */
	protected int m = 4;

	/**
	 * Selection mechanism used for selecting the tournament winner
	 */
	protected GoalBasedSelection<T,R> inner=null;

	/**
	 * Constructor: Create a tournament selection strategy with m players.
	 * @param m The number of players in the tournament
	 */
	public Tournament( int m ){
		this.inner = new Elitism<T,R>(1.0, 0.0);
		this.m = m;
	}

	/**
	 * Constructor: Create a tournament selection strategy with m players.
	 * @param m The number of players in the tournament
	 */
	public Tournament( Goal<T, R> goal, int m ){
		super( goal );
		this.inner = new Elitism<T,R>(goal, 1.0, 0.0);
		this.m = m;
	}

	/**
	 * Constructor: Create a tournament selection strategy with m players, using the given
	 * selection strategy for selecting the tournament winner.
	 * @param m The number of players in the tournament
	 * @param s The inner selection strategy for determining the tournament winner
	 */
	public Tournament( GoalBasedSelection<T,R> s, int m ){
		super(s.goal());
		this.m = m;
		this.inner = s;
	}

	@Override
	public void setGoal(Goal<T,R> goal){
		super.setGoal(goal);
		if(inner!=null) inner.setGoal(goal);
	}
	
	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param g Uniform integer number generator used for picking the candidate solution
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	 */
	protected int choose_one( IntUniform g, R[] x){
		@SuppressWarnings("unchecked")
		R[] candidates = (R[])new Object[m];
		int[] indices = new int[m];
		for( int i=0; i<m; i++ ){
			indices[i] = g.next();
			candidates[i] = x[indices[i]];
		}
		return indices[inner.choose_one(candidates)];
	}
  
	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param g Uniform integer number generator used for picking the candidate solution
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	 */
	@Override
	public int choose_one( R[] x ){
		return choose_one( new IntUniform(x.length), x );
	}  
  
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param q Quality associated to each candidate solution
	 * @return Indices of the selected candidate solutions
	 */
	@Override
	public int[] apply( int n, R[] x ){
		IntUniform g =  new IntUniform(x.length);
		int[] sel = new int[n];
		for (int i = 0; i<n; i++) sel[i] = choose_one(g, x);
		return sel;
	}
}