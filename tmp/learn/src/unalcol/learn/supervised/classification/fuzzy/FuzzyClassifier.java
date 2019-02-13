package unalcol.learn.supervised.classification.fuzzy;

import unalcol.math.function.Function;

public interface FuzzyClassifier<T> extends Function<T,double[]> {
    /**
     * Returns the number of classes that a data record belongs to
     * @return The number of classes that a data record belongs to
     */
    public int classesNumber();
}