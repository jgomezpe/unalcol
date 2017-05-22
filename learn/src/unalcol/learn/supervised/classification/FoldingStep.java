package unalcol.learn.supervised.classification;

import unalcol.algorithm.iterative.StepFunction;
import unalcol.data.PartitionedArrayCollection;
import unalcol.learn.supervised.InputOutputPair;
import unalcol.random.util.Partition;
import unalcol.types.collection.array.ArrayCollection;

public class FoldingStep<T> implements StepFunction<ArrayCollection<InputOutputPair<T,Integer>>,ConfussionMatrix> {
    protected int folds;
    protected ClassifierLearner<T> algorithm;

    /**
     * The partition done over the data set
     */
    protected PartitionedArrayCollection<InputOutputPair<T,Integer>> partition = null;
    
    protected int real_classes;
    protected int[][] groups;

    public FoldingStep( int folds) {
	this.folds = folds;
    }
    
    @Override
    public ConfussionMatrix apply(ConfussionMatrix arg0) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ConfussionMatrix init(ArrayCollection<InputOutputPair<T,Integer>> input) {
	      if( real_classes == 0){
		  LabeledArrayCollectionUtil<T> util = new LabeledArrayCollectionUtil<T>();
	          real_classes = util.classes(input).length;
	          groups = util.separatedByClass(input, real_classes);
	      }
	      ConfussionMatrix output = new ConfussionMatrix(real_classes, real_classes);
	      Partition p = new StratifiedPartition(groups, real_classes, true);
	      partition = new PartitionedArrayCollection<InputOutputPair<T,Integer>>(input, p, 0, false);
	      return output;
    }
}