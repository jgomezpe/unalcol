package unalcol.learn.unsupervised.partition;

import unalcol.algorithm.iterative.StepFunction;
import unalcol.math.algebra.VectorSpace;
import unalcol.random.integer.IntUniform;
import unalcol.random.real.UniformGenerator;
import unalcol.types.collection.array.ArrayCollection;

public class kMeansStep<T> implements StepFunction<ArrayCollection<T>, T[]>{
    protected int groups;
    protected ArrayCollection<T> data;
    protected VectorSpace<T> space;
    
    public kMeansStep(int groups, VectorSpace<T> space){
	this.groups = groups;
	this.space = space;
    }

    @Override
    public T[] apply(T[] centroids) {
	// TODO Auto-generated method stub
	return null;
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