/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import java.io.FileWriter;
import unalcol.data.plaintext.RealVectorFile;
import unalcol.dynamic.rain.interactionfunction.GravityLaw;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.RealVectorLinealScale;

/**
 *
 * @author jgomez
 */
public class RainTest {
  
  public static void main(String[] args){
      try{
            String fileName = "/home/jgomez/Repository/data/chameleon/t7.10k.dat";
            Vector<double[]> v = RealVectorFile.load(fileName, ' ');
            RealVectorLinealScale scale = new RealVectorLinealScale(v);
            scale.fastApply(v);
            double EPSILON = 0.0001;
            double dmm = 1.0;
            int D = 3;
            double G = 1.33514404296875E-5;
            double G_scale = 0.9;
            GravityLaw gravity = new GravityLaw(G, D, dmm, G_scale);
            EuclideanMove move = new EuclideanMove(gravity,EPSILON);
            PickOne<double[]> pick = new SimplestPickOne();
            RainSystem<double[]> rain = new RainSystem(v, move, pick);
            RainStopPredicate predicate = new RainStopPredicate();
            rain.simulate(predicate);
            FileWriter w = new FileWriter("/home/jgomez/Repository/data/rgc.txt");
            int[] str = rain.structures();
            for(int i=0; i<str.length; i++){
                w.write(""+str[i]+"\n");                
            }
            w.close();
      }catch(Exception e){
          e.printStackTrace();
      }      
      
  }

    
}
