package unalcol.learn.supervised.classification.fuzzy;

import unalcol.learn.Prediction;
import unalcol.learn.fuzzy.Aggregator;
import unalcol.learn.supervised.classification.Classifier;

public class FuzzyToCrispClassifier<T> implements Classifier<T> {
    protected FuzzyClassifier<T> fuzzy;
    protected Aggregator aggregator;
    
    public FuzzyToCrispClassifier(FuzzyClassifier<T> fuzzy, Aggregator aggregator ) {
	this.aggregator = aggregator;
	this.fuzzy = fuzzy;
    }
    
    @Override
    public Prediction<Integer> apply(T obj) {
	return aggregator.apply(fuzzy.apply(obj));
    }
}