package unalcol.real.pick;

public class Tournament extends Uniform{
	  /**
	   * The tournament size
	   */
	  protected int m = 4;

	  /**
	   * Selection mechanism used for selecting the tournament winner
	   */
	  protected Pick inner = new Elitism(1.0,0.0);

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
	  public Tournament( int m, Pick s ){
	    this.m = m;
	    this.inner = s;
	  }

	  /**
	   * Selects a candidate solution from a set of candidate solutions
	   * @param g Uniform integer number generator used for picking the candidate solution
	   * @param q Quality associated to each candidate solution
	   * @return Index of the selected candidate solution
	   */
	  @Override
	  protected int choose_one( unalcol.integer.Uniform g, double[] q ){
	    double[] candidates = new double[m];
	    unalcol.integer.Array indices = new unalcol.integer.Array();
	    for( int i=0; i<m; i++ ){
	        indices.add(g.next());
	        candidates[i] = q[indices.get(i)];
	    }
	    return indices.get(inner.choose_one(candidates));
	  }
}