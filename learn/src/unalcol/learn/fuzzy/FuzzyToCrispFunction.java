package unalcol.learn.fuzzy;

import unalcol.learn.Prediction;
import unalcol.math.function.Function;

public class FuzzyToCrispFunction<S,T> implements Function<S,Prediction<T>> {
    protected Function<S,FuzzyPrediction<T>> function;
    protected Aggregator aggregator;
    
    public FuzzyToCrispFunction(Function<S,FuzzyPrediction<T>> function, Aggregator aggregator) {
	this.function = function;
	this.aggregator = aggregator;
    }

    @Override
    public Prediction<T> apply(S x) {
	return (function.apply(x)).crisp(aggregator);
    }
}