package unalcol.learn.supervised.classification;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.algorithm.iterative.IterativeAlgorithm;
import unalcol.learn.supervised.InputOutputPair;
import unalcol.types.collection.array.ArrayCollection;


/**
 * <p>Title: FoldingExperiment</p>
 * <p>Description: A set of experiments over an algorithm using a k-folding strategy.
 * A k-folding strategy divides the data source en k groups of similar size.
 * each k-1 sets are used to train the classifier and the remaining set is used
 * to test the classifier trained. The process is repeated for each data set</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez-Perdomo
 * @version 1.0
 *
 */

public class FoldingExperiment<T> extends 
        IterativeAlgorithm<ArrayCollection<InputOutputPair<T,Integer>>, ConfussionMatrix>{
 /**
   * Constructor: Creates a folding experiment of the algorithm given.
   * The experiment is the execution of the algorithm the number of times desired
   * @param _n Number of times the algorithm will be executed
   * @param _algorithm Algorithm to be executed
   * @param _source Data source used to generate the training and the testing sets
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
public FoldingExperiment( int n, ClassifierLearner<T> algorithm ) {
      super( new FoldingStep<T>(n),  new ForLoopCondition(n) );
  }
}