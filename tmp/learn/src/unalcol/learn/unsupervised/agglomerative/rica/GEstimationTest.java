/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.agglomerative.rica;

import unalcol.data.plaintext.RealVectorFile;
import unalcol.dynamic.rain.*;
import unalcol.dynamic.rain.interactionfunction.GravityLaw;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.RealVectorLinealScale;
import unalcol.types.real.array.metrics.Euclidean;

/**
 *
 * @author jgomez
 */
public class GEstimationTest {
  public static void main(String[] args){
      try{
            String fileName = "/home/jgomez/Repository/data/chameleon/t7.10k.dat";
            Vector<double[]> v = RealVectorFile.load(fileName, ' ');
            RealVectorLinealScale scale = new RealVectorLinealScale(v);
            scale.fastApply(v);
            int D = 2;
            int N = v.size();
            PickOne<double[]> pick = new SimplestPickOne();
            double EPSILON = 0.001;
            double dmm = 1.0; //(new PyramidalMinMaxDistance()).compute(N, D-1);
            double G_scale = 0.99;    
            int ITERS = 30;
            EuclideanMove move = new EuclideanMove(null, EPSILON);
            GravityLawEstimation<double[]> gest = 
                    new TrialIterationsGravityLawEstimation(3, dmm, G_scale, pick, ITERS);
            double G = gest.G(v, move);
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
