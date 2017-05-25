package unalcol.learn.fuzzy;

import unalcol.algorithm.Algorithm;
import unalcol.math.function.Function;

public abstract class FuzzyLearner<Q,S,T> extends Algorithm<Q, Function<S,FuzzyPrediction<T>>>{}