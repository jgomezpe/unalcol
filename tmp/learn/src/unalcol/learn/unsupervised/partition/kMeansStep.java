package unalcol.learn.unsupervised.partition;

import java.util.Iterator;

import unalcol.algorithm.iterative.StepFunction;
import unalcol.learn.Prediction;
import unalcol.learn.fuzzy.MinAggregator;
import unalcol.learn.supervised.classification.fuzzy.FuzzyToCrispClassifier;
import unalcol.math.algebra.VectorSpace;
import unalcol.math.metric.QuasiMetric;
import unalcol.random.integer.IntUniform;
import unalcol.types.collection.Collection;
import unalcol.types.collection.array.ArrayCollection;

public class kMeansStep<T> implements StepFunction<ArrayCollection<T>, T[]>{
	protected double s;
	protected int groups;
	protected ArrayCollection<T> data;
	protected VectorSpace<T> space;
	protected QuasiMetric<T> metric;
    
	public kMeansStep(int groups, VectorSpace<T> space, QuasiMetric<T> metric){
		this.groups = groups;
		this.space = space;
		this.metric = metric;
	}

    @Override
	public T[] apply(T[] centroids) {
        @SuppressWarnings("unchecked")
		T[] next_centroids = (T[])new Object[groups];
        for( int i=0; i<groups; i++ ){
            next_centroids[i] = space.identity(centroids[0]);
        }
        CentroidsRecognizer<T> recognizer = new CentroidsRecognizer<T>(centroids, metric);
        FuzzyToCrispClassifier<T> classifier = new FuzzyToCrispClassifier<>(recognizer, new MinAggregator());
        s = 0.0;
        int[] counter = new int[groups];
        Collection<Prediction<Integer>> label = classifier.apply(data);
        Iterator<T> iter = data.iterator();
        int c;
        for( Prediction<Integer> p : label ){
            c = p.label();
            s += p.confidence();
            next_centroids[c] = space.fastPlus(next_centroids[c], iter.next());
            counter[c]++;
        }
        for( int i=0; i<groups; i++ ){
             if( counter[i] > 0 ){
               space.fastDivide( next_centroids[i], counter[i] );
             }           
        }
        return next_centroids;
	}

    @SuppressWarnings("unchecked")
    @Override
	public T[] init(ArrayCollection<T> data){
		this.data = data;
		IntUniform g = new IntUniform(data.size());
		int[] index = g.generate(groups);
		T[] centroids = (T[])new Object[groups];
		for( int i=0; i<groups; i++ ){
			centroids[i] = data.get(index[i]);
		}
		return centroids;
    }
}