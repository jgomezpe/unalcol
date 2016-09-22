/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.agglomerative.rica;

import unalcol.data.plaintext.SparseRealVectorFile;
import unalcol.dynamic.rain.PickOne;
import unalcol.dynamic.rain.SimplestPickOne;
import unalcol.dynamic.rain.SparseSphereMove;
import unalcol.dynamic.rain.interactionfunction.GravityLaw;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorSphereNormalization;

/**
 *
 * @author jgomez
 */
public class SphereGEstimationTest {
public static void main(String[] args){
      try{
            String fileName = "/home/jgomez/Repository/data/misc/datasets/tr11.mat";
            Vector<SparseRealVector> v = SparseRealVectorFile.load(fileName, ' ');
            SparseRealVectorSphereNormalization scale = new SparseRealVectorSphereNormalization();
            scale.fastApply(v);
            int D = v.get(0).dim();
            int N = v.size();
            PickOne<SparseRealVector> pick = new SimplestPickOne();
            double EPSILON = 0.3;
            double dmm = 1.0; //(new PyramidalMinMaxDistance()).compute(N, D-1);
            double G_scale = 0.99;    
            int ITERS = 30;
            GravityLawEstimation<SparseRealVector> gest = 
                    new TrialIterationsGravityLawEstimation(3, dmm, G_scale, pick, ITERS);
            double G = gest.G(v, new SparseSphereMove(null, EPSILON));
            GravityLaw gravity = new GravityLaw(G, 3, dmm, G_scale);
//            EuclideanMove move = new EuclideanMove(gravity,EPSILON);
//            RainSystem<double[]> rain = new RainSystem(v, move, pick);
//            RainStopPredicate predicate = new RainStopPredicate();
//            rain.simulate(predicate);
      }catch(Exception e){
          e.printStackTrace();
      }      
      
  }        
}
