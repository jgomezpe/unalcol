package unalcol.learn.fuzzy;

import unalcol.learn.Learner;
import unalcol.learn.Prediction;
import unalcol.math.function.Function;

public class FuzzyToCrispLearner<Q,S,T> extends Learner<Q,S,T>{
    protected FuzzyLearner<Q,S,T> fuzzy;
    protected Aggregator aggregator;

    public FuzzyToCrispLearner(FuzzyLearner<Q,S,T> fuzzy, Aggregator aggregator) {
	this.fuzzy = fuzzy;
	this.aggregator = aggregator;
    }

    @Override
    public Function<S, Prediction<T>> apply(Q x) {
	return new FuzzyToCrispFunction<S,T>(fuzzy.apply(x), aggregator);
    }
}