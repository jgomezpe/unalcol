package unalcol.search.selection;

import unalcol.types.collection.vector.*;
import unalcol.random.integer.IntUniform;

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
 public class Tournament<T> extends Uniform<T>{
  /**
   * The tournament size
   */
  protected int m = 4;

  /**
   * Selection mechanism used for selecting the tournament winner
   */
  protected Selection<T> inner = new Elitism<T>(1.0,0.0);

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
  public Tournament( int m, Selection<T> s ){
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
  protected int choose_one( IntUniform g, Vector<Double> q ){
    Vector<Double> candidates = new Vector<Double>();
    Vector<Integer> indices = new Vector<Integer>();
    for( int i=0; i<m; i++ ){
        indices.add(g.generate());
        candidates.add( q.get( indices.get(i) ) );
    }
    return indices.get(inner.choose_one(candidates));
  }
}