package unalcol.learn.fuzzy;

public class SimpleFuzzyPrediction<T> implements FuzzyPrediction<T> {
    protected double[] confidences;
    protected T[] labels;
   public SimpleFuzzyPrediction(T[] labels, double[] confidences ) {
       this.confidences = confidences;
       this.labels = labels;
   }
   @Override
   public T[] labels() {
       return labels;
   }
   @Override
   public double[] confidences() {
       return confidences;
   }
}
