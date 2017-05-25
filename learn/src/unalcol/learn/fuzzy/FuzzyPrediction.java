package unalcol.learn.fuzzy;

import unalcol.learn.Prediction;
import unalcol.learn.SimplePrediction;

public interface FuzzyPrediction<T>{
    public T[] labels();
    public double[] confidences();
    
    public default Prediction<T> crisp(Aggregator aggregator){
	double[] c = confidences();
	int index = aggregator.apply(c).label();
	return new SimplePrediction<T>(labels()[index], c[index]);
    }
}