/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn;

import unalcol.types.integer.array.IntArray;

/**
 *
 * @author jgomez
 */
public class MapLabelRecognizer<T> extends Recognizer<T> {
    protected int n;
    protected int[] labels;
    protected Recognizer<T> base;
    protected double[][] mappedConfidences;
    
    public MapLabelRecognizer( int[] labels, Recognizer<T> base ){
        this( labels, base, new MaxAggregator() );
    }

    public MapLabelRecognizer( int[] labels, Recognizer<T> base, Aggregator aggregator ){
        super( aggregator );
        this.base = base;
        this.labels = labels;
        n= IntArray.max(labels) + 1;

        int[] counter = new int[n];
        for( int i=0; i<labels.length; i++){
            counter[labels[i]]++;
        }
        
        mappedConfidences = new double[n][];
        for( int i=0; i<n; i++ ){
            mappedConfidences[i] = new double[counter[i]];
        }
    }

    @Override
    public int classesNumber() {
        return n;
    }

    @Override
    public double[] confidence(T data) {
        double[] conf = base.confidence(data);
        int[] counter = new int[n];
        int k;
        for( int i=0; i<labels.length; i++){
            k = labels[i];
            mappedConfidences[k][counter[k]] = conf[i];
            counter[k]++;
        }
        conf = new double[n];
        for( int i=0; i<n; i++ ){
                conf[i] = aggregator.apply(mappedConfidences[i]).confidence();
        }
        return conf;
    }    
}