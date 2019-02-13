/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.partition;

import unalcol.types.collection.vector.Vector;
import unalcol.clone.Clone;
import unalcol.data.plaintext.LabelsFile;
import unalcol.data.plaintext.RealVectorFile;
import unalcol.learn.MapLabelRecognizer;
import unalcol.learn.supervised.classification.ConfussionMatrix;
import unalcol.learn.supervised.classification.fuzzy.FuzzyConfussionMatrix;
import unalcol.random.integer.RandInt;
import unalcol.random.integer.IntUniform;
import unalcol.types.integer.array.IntArray;
import unalcol.types.real.array.RealVectorSpace;
import unalcol.types.real.array.metrics.Euclidean;
import unalcol.types.real.array.sparse.*;

/**
 *
 * @author jgomez
 */
public class KMeansDemo {
    
  public static void print( int[] a ){
        System.out.println("$$$$$$$$$$$$$$$$");
        for( int i=0; i<a.length; i++ ){
            System.out.println(a[i]);
        }
        System.out.println("$$$$$$$$$$$$$$$$");
  }
  
  public static void main(String[] args){
      try{
            String fileName = "/home/jgomez/Repository/data/UCI/pima";
            Vector<double[]> v = RealVectorFile.load(fileName+".dat", ' ');
            int[] real = LabelsFile.load(fileName+".label");
            //print(real);
            int k = IntArray.max(real) + 1;
            System.out.println( "#K"+k);
            RandInt g = new IntUniform(v.size());
            double[][] mu = new double[k][];            
            for( int i=0; i<k; i++){
                mu[i] = (double[])Clone.create(v.get(g.next()));
            }
            Euclidean metric = new Euclidean();
            RealVectorSpace space = new RealVectorSpace();
            CentroidsRecognizer r = 
                    new CentroidsRecognizer(mu,metric,false);
            kMeans<double[]> kmeans = new kMeans(r, space, 0.001, 100);
            r = (CentroidsRecognizer)kmeans.apply(v);
            int[] pred = r.label(v);
            //print(pred);
            int pk = IntArray.max(pred) + 1;
            FuzzyConfussionMatrix cm = new ConfussionMatrix(k,pk);
            cm.add(real, pred);
            System.out.println("Without organizing .." + cm.softAccuracy());
            System.out.println(cm.mutual_information());
            int[] opt_labels = cm.optimal_labels();
            MapLabelRecognizer<double[]> mr = new MapLabelRecognizer<>(opt_labels, r);
            pred = mr.label(v);
            pk = IntArray.max(pred) + 1;
            cm = new ConfussionMatrix(k,pk);
            cm.add(real, pred);
            System.out.println("Organizing .." + cm.accuracy());
            System.out.println(cm.mutual_information());
      }catch(Exception e){
          e.printStackTrace();
      }      
      
  }

    
}
