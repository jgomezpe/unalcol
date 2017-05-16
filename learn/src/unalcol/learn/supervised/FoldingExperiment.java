package unalcol.learn.supervised;

import unalcol.learn.Prediction;
import unalcol.learn.supervised.classification.ClassifierLearner;
import unalcol.learn.supervised.classification.ConfussionMatrix;
import unalcol.math.function.Function;
import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.algorithm.iterative.IterativeAlgorithm;
import unalcol.data.PartitionedArrayCollection;
import unalcol.random.util.Partition;
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

  protected ClassifierLearner<T> algorithm;

  /**
   * The partition done over the data set
   */
  protected PartitionedArrayCollection<InputOutputPair<T,Integer>> partition = null;
  
  protected int real_classes;
  protected int[][] groups;

  /**
   * Constructor: Creates a folding experiment of the algorithm given.
   * The experiment is the execution of the algorithm the number of times desired
   * @param _n Number of times the algorithm will be executed
   * @param _algorithm Algorithm to be executed
   * @param _source Data source used to generate the training and the testing sets
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public FoldingExperiment( int n, ClassifierLearner<T> algorithm ) {
      super( new ForLoopCondition(n) );
      this.algorithm = algorithm;
  }

    /**
     * Determines the output produced by the iterative algorithm if no iterations are performed
     * @param input The algorithm input
     * @return O The output produced by the iterative algorithm if no iterations are performed
     */
  public ConfussionMatrix nonIterOutput(ArrayCollection<InputOutputPair<T,Integer>> input){
      if( real_classes == 0){
	  LabeledArrayCollectionUtil<T> util = new LabeledArrayCollectionUtil<T>();
          real_classes = util.classes(input).length;
          groups = util.separatedByClass(input, real_classes);
      }
      output = new ConfussionMatrix(real_classes, real_classes);
      Partition p = new StratifiedPartition(groups, real_classes, true);
      partition = new PartitionedArrayCollection<InputOutputPair<T,Integer>>(input, p, 0, false);
      return output;
  }

    @Override
    public ConfussionMatrix iteration(int k, ArrayCollection<InputOutputPair<T,Integer>> input, ConfussionMatrix output) {
        partition.init(k, false);
        Function<T,Prediction<Integer>> r = algorithm.apply(partition);
        partition.init(k,true);
        Prediction<Integer>[] pred = r.apply(new RemovedOutputCollection<T,Integer>(partition));
        for( int i=0; i<pred.length; i++ ){
            output.add(partition.get(i).output(), pred[i].label());
        }
        return output;
    }
}
