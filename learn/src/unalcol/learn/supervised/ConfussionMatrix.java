package unalcol.learn.supervised;

import unalcol.types.real.matrix.DoubleMatrixUtil;

/**
 * <p>Title: ConfussionMatrix</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company:  Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public abstract class ConfussionMatrix{
  protected int r;
  protected int p;
  protected double[][] matrix;
  protected double[] real_total;
  protected double[] predicted_total;
  protected double total;

  public ConfussionMatrix( int real_classes, int predicted_classes){
      r = real_classes;
      p = predicted_classes; 
      matrix = new double[r+1][p+1];
      real_total = new double[r+1];
      predicted_total = new double[p+1];
      total = 0.0;
  }
  
  public void add( int real, int predicted, double confidence ){
      if(real<0) real = r;
      if(predicted<0) predicted = p;
      matrix[real][predicted] += confidence;
      real_total[real] += confidence;      
      predicted_total[predicted] += confidence;
      total += confidence;
  }
  
  public void add( int[] real, int[] predicted ){
      for( int i=0; i<real.length; i++){          
          add( real[i], predicted[i] );
      }
  }

  public void add( int real, int predicted ){
      add( real, predicted, 1.0 );
  }
  
  public void add( ConfussionMatrix cm ){
      for( int i=0; i<real_total.length; i++ ){
        for( int j=0; j<predicted_total.length; j++ ){
            matrix[i][j] += cm.matrix[i][j];
        }
        real_total[i] += cm.real_total[i];
      }
      for( int j=0; j<predicted_total.length; j++ ){
          predicted_total[j] += cm.predicted_total[j];
      }
      total += cm.total;
  }
  
  public double noise_detection(){
      if( real_total[r] == 0.0 ) return 0.0;
      return matrix[r][p]/real_total[r];
  }
  
  public double false_noise(){
      return (predicted_total[p]-matrix[r][p])/(total-real_total[r]);
  }
  
    public int[] optimal_labels(){
        int[] pl = new int[p];
        for( int k=0; k<p; k++){
            pl[k] = 0;
            for( int i=1; i<r; i++ ){
                if( matrix[pl[k]][k] < matrix[i][k] ){
                    pl[k] = i;
                }
            }
            if(matrix[pl[k]][k]==0){ pl[k] = -1; }
        }
        return pl;        
    }
    
    public double mutual_information(){
        double[] nh = new double[matrix[0].length];
        for( int j=0; j<matrix[0].length; j++){
            nh[j] = 0.0;
            for( int i=0; i<matrix.length; i++){
                nh[j] += matrix[i][j];
            }
            //System.out.print("\t"+nh[j]);
        }
        //System.out.println();
        double n = 0.0;
        double[] nl = new double[matrix.length];
        for( int i=0; i<matrix.length; i++){
            nl[i] = 0.0;
            for( int j=0; j<matrix[i].length; j++){
                nl[i] += matrix[i][j];
            }
            //System.out.print("\t"+nl[i]);
            n += nl[i];
        }
        //System.out.println();
        
        double score = 0.0;
        double[][] nlh = new double[nl.length][nh.length];
        for( int i=0; i<nl.length; i++ ){
            for( int j=0; j<nh.length; j++ ){
                nlh[i][j]=n*matrix[i][j]/(nl[i]*nh[j]);
                if( nlh[i][j] > 0 ){
                    nlh[i][j] = matrix[i][j]*Math.log(nlh[i][j]);
                    score += nlh[i][j];
                }
            }
        }
        
        double snl = 0.0;
        for( int j=0; j<nl.length; j++){
            if( nl[j] > 0.0 ){
                nl[j] *= Math.log(nl[j]/n);
                snl += nl[j];
            }
        }

        double snh = 0.0;
        for( int j=0; j<nh.length; j++){
            if( nh[j] > 0.0 ){
                nh[j] *= Math.log(nh[j]/n);
                snh += nh[j];
            }
        }
        double tmp = Math.sqrt(snh*snl);
        if( tmp == 0 )
            score = 0;
        else
            score /= tmp;
        return score;
    }
    
    public double softAccuracy(){
        double acc = 0.0;
        for( int j=0; j<matrix[0].length; j++){
            double max = matrix[0][j];
            for( int i=1; i<matrix.length; i++ ){
                if( max < matrix[i][j] ){
                    max = matrix[i][j];
                }
            }
            acc += max;
        }
        return acc / total;        
    }
    
    
  
  /**
   * Gets accuracy
   * @return values
   */
  public double accuracy() {
	  return DoubleMatrixUtil.diagonalSum(matrix)/total;
  }

  /**
   * Gets Detection Rate
   * @return values
   */
  public double detection_rate(int[] positive_class) {
      double a = 0.0;
      double b = 0.0;
      for( int i=0; i<positive_class.length; i++ ){
          for( int j=0; j<positive_class.length; j++ ){
              if( positive_class[j] < p)
                  a += matrix[positive_class[i]][positive_class[j]];
          }
          b += real_total[positive_class[i]];
      }
      return a/b;
  }
  /**
   * Gets False Alarm
   * @return values
   */
  public double false_alarm( int[] negative_class ) {
      double a = 0.0;
      double b = 0.0;
      for( int i=0; i<negative_class.length; i++ ){
          for( int j=0; j<negative_class.length; j++ ){
            if( negative_class[j] < p)
                a += matrix[negative_class[i]][negative_class[j]];
          }
          b += real_total[negative_class[i]];
      }
      return 1.0 - a/b;
  }  
}
