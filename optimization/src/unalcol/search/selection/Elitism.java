package unalcol.search.selection;

import unalcol.random.integer.IntRoulette;
import unalcol.sort.Order;
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
public class Elitism<T> extends Selection<T>{

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
  
  protected class IndexQ{
      public int index;
      public double q;      
      public IndexQ( int index, double q ){
          this.index = index;
          this.q = q;
      }
  }
  
  protected class IndexQOrder extends Order<IndexQ>{

        @Override
        public int compare(IndexQ one, IndexQ two) {
            return (int)(one.q-two.q);
        }
  }

  /**
   * Selects a subset of candidate solutions from a set of candidates
   * @param n Number of candidate solutions to be selected
   * @param q Quality associated to each candidate solution
   * @return Indices of the selected candidate solutions
   */
  @Override
  public Vector<Integer> apply( int n, double[] q ){
      Vector<Integer> sel = new Vector<Integer>();
      int s = q.length;
      SortedVector<IndexQ> indexq = new SortedVector<>( new ReversedOrder<>(new IndexQOrder()));
      for( int i=0; i<s; i++ ){
          indexq.add(new IndexQ(i,q[i]));
      }
      int m = (int) (s * elite_percentage);
      for (int i = 0; i < n && i < m; i++) {
          sel.add(indexq.get(i).index);
      }
      if( m<n ){
          int k = (int) (s * (1.0 - cull_percentage));
          double[] weight = new double[k];
          double total = k * (k + 1) / 2.0;
          for (int i = 0; i < k; i++) {
              weight[i] = (k - i) / total;
          }
          IntRoulette generator = new IntRoulette(weight);
          n -= m;
          int[] index = generator.generate(n);
          for (int i=0; i<n; i++) {
              sel.add(indexq.get(index[i]).index);
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
  public int choose_one( double[] q ){
      int k = 0;
      for( int i=1; i<q.length; i++ ){
          if( q[k] < q[i] ){
              k = i;
          }
      }
      return k;
  }
}