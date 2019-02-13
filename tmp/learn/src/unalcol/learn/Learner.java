package unalcol.learn;

import unalcol.algorithm.Algorithm;
import unalcol.math.function.Function;

public abstract class Learner<Q,S,T> extends Algorithm<Q, Function<S,Prediction<T>>>{}
