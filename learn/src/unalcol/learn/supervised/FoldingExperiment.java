package unalcol.learn.supervised;

import unalcol.learn.Prediction;
import unalcol.learn.Recognizer;
import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.algorithm.iterative.IterativeAlgorithm;
import unalcol.data.PartitionedArrayCollection;
import unalcol.random.util.Partition;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.vector.Vector;


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

public class FoldingExperiment extends 
        IterativeAlgorithm<ArrayCollection<LabeledObject>, ConfussionMatrix>{

  protected SupervisedLearningFromArray algorithm;

  /**
   * The partition done over the data set
   */
  protected PartitionedArrayCollection<LabeledObject> partition = null;
  
  protected int real_classes;
  protected int[][] groups;

  /**
   * Constructor: Creates a folding experiment of the algorithm given.
   * The experiment is the execution of the algorithm the number of times desired
   * @param _n Number of times the algorithm will be executed
   * @param _algorithm Algorithm to be executed
   * @param _source Data source used to generate the training and the testing sets
   */
  public FoldingExperiment( int n, SupervisedLearningFromArray algorithm ) {
    super( new ForLoopCondition(n) );
    this.algorithm = algorithm;
  }

    /**
     * Determines the output produced by the iterative algorithm if no iterations are performed
     * @param input The algorithm input
     * @return O The output produced by the iterative algorithm if no iterations are performed
     */
  public ConfussionMatrix nonIterOutput(ArrayCollection<LabeledObject> input){
      if( real_classes == 0){
          real_classes = LabeledArrayCollectionUtil.classes(input).length;
          groups = LabeledArrayCollectionUtil.separatedByClass(input, real_classes);
      }
      output = new ClassicConfussionMatrix(real_classes, real_classes);
      Partition p = new StratifiedPartition(groups, real_classes, true);
      partition = new PartitionedArrayCollection(input, p, 0, false);
      return output;
  }

    @Override
    public ConfussionMatrix iteration(int k, ArrayCollection<LabeledObject> input, ConfussionMatrix output) {
        partition.init(k, false);
        Recognizer r = algorithm.apply(partition);
        partition.init(k,true);
        Vector<Prediction> pred = r.predict(new RemovedLabelArrayCollection(partition));
        for( int i=0; i<pred.size(); i++ ){
            output.add(partition.get(i).label(), pred.get(i).label());
        }
        return output;
    }  
}
