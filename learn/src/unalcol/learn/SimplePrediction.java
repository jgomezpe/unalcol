package unalcol.learn;

public class SimplePrediction<T> implements Prediction<T> {
    /**
    *
    */
   public T label;

   /**
    *
    */
   public double confidence;

   /**
    *
    * @param _label
    * @param _confidence
    */
   public SimplePrediction( T _label, double _confidence ) {
     label = _label;
     confidence = _confidence;
   }

   /**
    *
    * @param _label
    */
   public SimplePrediction( T _label ) {
     label = _label;
     confidence = 1.0;
   }
   
   @Override
   public T label(){
       return label;
   }
   
   @Override
   public double confidence(){
       return confidence;
   }

}
