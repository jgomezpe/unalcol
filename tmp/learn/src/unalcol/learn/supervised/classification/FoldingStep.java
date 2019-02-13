package unalcol.learn.supervised.classification;

import java.util.Iterator;

import unalcol.algorithm.iterative.StepFunction;
import unalcol.data.PartitionedArrayCollection;
import unalcol.learn.Prediction;
import unalcol.learn.supervised.InputOutputPair;
import unalcol.learn.supervised.RemovedInputCollection;
import unalcol.learn.supervised.RemovedOutputCollection;
import unalcol.math.function.Function;
import unalcol.random.util.Partition;
import unalcol.types.collection.Collection;
import unalcol.types.collection.array.ArrayCollection;

public class FoldingStep<T> implements StepFunction<ArrayCollection<InputOutputPair<T,Integer>>,ConfussionMatrix> {
    protected int folds;
    protected int k;
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
    public ConfussionMatrix apply(ConfussionMatrix cm) {
        partition.init(k, false);
        Function<T,Prediction<Integer>> r = algorithm.apply(partition);
        partition.init(k,true);
        k++;
        Collection<Prediction<Integer>> pred = r.apply(new RemovedOutputCollection<T,Integer>(partition));
        Collection<Integer> label = new RemovedInputCollection<T,Integer>(partition);
        Iterator<Integer> label_iter = label.iterator();
        for( Prediction<Integer> p : pred ){
            cm.add(label_iter.next(), p.label() );
        }
	return cm;
    }

    @Override
    public ConfussionMatrix init(ArrayCollection<InputOutputPair<T,Integer>> input) {
	      if( real_classes == 0){
		  LabeledArrayCollectionUtil<T> util = new LabeledArrayCollectionUtil<T>();
	          real_classes = util.classes(input).length;
	          groups = util.separatedByClass(input, real_classes);
	      }
	      Partition p = new StratifiedPartition(groups, folds, true);
	      partition = new PartitionedArrayCollection<InputOutputPair<T,Integer>>(input, p, 0, false);
	      return new ConfussionMatrix(real_classes, real_classes);
    }
}