/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.partition;

import unalcol.clone.Clone;
import unalcol.data.plaintext.LabelsFile;
import unalcol.data.plaintext.SparseRealVectorFile;
import unalcol.learn.MapLabelRecognizer;
import unalcol.learn.supervised.ClassicConfussionMatrix;
import unalcol.learn.supervised.ConfussionMatrix;
import unalcol.random.integer.RandInt;
import unalcol.random.integer.IntUniform;
import unalcol.types.collection.vector.Vector;
import unalcol.types.integer.IntUtil;
import unalcol.types.integer.array.IntArray;
import unalcol.types.real.array.sparse.*;

/**
 *
 * @author jgomez
 */
public class SphericalKMeansDemo {
    
  public static void normalize(Vector<SparseRealVector> v ){
      SparseRealVectorDotProduct product = new SparseRealVectorDotProduct();
      SparseRealVectorSpace scale = new SparseRealVectorSpace();
      for( int i=0; i<v.size(); i++ ){
          scale.fastDivide(v.get(i), product.norm(v.get(i)));
      }    
  }  
  
  public static void print( int[] a ){
        System.out.println("$$$$$$$$$$$$$$$$");
        for( int i=0; i<a.length; i++ ){
            System.out.println(a[i]);
        }
        System.out.println("$$$$$$$$$$$$$$$$");
  }
  
  public static void main(String[] args){
      try{
            String fileName = "/home/jgomez/Repository/data/misc/datasets/tr11.mat";
            Vector<SparseRealVector> v = SparseRealVectorFile.load(fileName, ' ');
            int[] real = LabelsFile.load(fileName+".rclass");
            //print(real);
            SparseRealVectorSphereNormalization scale = new SparseRealVectorSphereNormalization();
            scale.fastApply(v);
            int k = IntArray.max(real) + 1;
            RandInt g = new IntUniform(v.size());
            SparseRealVector[] mu = new SparseRealVector[k];            
            for( int i=0; i<k; i++){
                mu[i] = (SparseRealVector)Clone.create(v.get(g.next()));
            }
            SparseRealVectorCosineSimilarity metric = new SparseRealVectorCosineSimilarity();
            SparseRealVectorSpace space = new SparseRealVectorSpace();
            SphereSparseCentroidsRecognizer r = 
                    new SphereSparseCentroidsRecognizer(mu,metric,true);
            kMeans<SparseRealVector> kmeans = new kMeans(r, space, 0.001, 100);
            r = (SphereSparseCentroidsRecognizer)kmeans.apply(v);
            int[] pred = r.label(v);
            //print(pred);
            int pk = IntArray.max(pred) + 1;
            ConfussionMatrix cm = new ClassicConfussionMatrix(k,pk);
            cm.add(real, pred);
            System.out.println("Without organizing .." + cm.softAccuracy());
            System.out.println(cm.mutual_information());
            int[] opt_labels = cm.optimal_labels();
            MapLabelRecognizer<SparseRealVector> mr = new MapLabelRecognizer<>(opt_labels, r);
            pred = mr.label(v);
            pk = IntArray.max(pred) + 1;
            cm = new ClassicConfussionMatrix(k,pk);
            cm.add(real, pred);
            System.out.println("Organizing .." + cm.accuracy());
            System.out.println(cm.mutual_information());
      }catch(Exception e){
          e.printStackTrace();
      }      
      
  }

    
}
