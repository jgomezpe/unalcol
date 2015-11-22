/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.agglomerative.rica;

import unalcol.dynamic.rain.AttractionMove;
import unalcol.dynamic.rain.PickOne;
import unalcol.dynamic.rain.RainSystem;
import unalcol.dynamic.rain.interactionfunction.GravityLaw;
import unalcol.types.collection.array.ArrayCollection;

/**
 *
 * @author jgomez
 */
public class TrialIterationsGravityLawEstimation<T> extends GravityLawEstimation<T> {
    protected int ITERS = 1;
    
    public TrialIterationsGravityLawEstimation( int D, double dmm, 
            double G_scale, PickOne<T> pick){
        super( D, dmm, G_scale, pick);
    }
    
    public TrialIterationsGravityLawEstimation( int D, double dmm, 
            double G_scale, PickOne<T> pick, int ITERS){
        super( D, dmm, G_scale, pick);
        this.ITERS = ITERS;
    }
    
    protected int fusions(RainSystem<T> rain, int FUSIONS){
        int counter=0;
        for( int i=0; i<ITERS && counter < FUSIONS; i++){
            rain.step();
            counter += rain.structureFusions();
            System.out.print(" "+counter);
        }
        System.out.println();
        return counter;
    }
    
    @Override
    public double G(ArrayCollection<T> set, AttractionMove<T> move) {
        GravityLaw gs;
        double G = 0.5;
        int N = set.size();
        int MIN_FUSIONS =(int)(ITERS*Math.sqrt(N)/2);
        int EPSILON_FUSIONS = (int)(Math.sqrt(2*MIN_FUSIONS)); 
        RainSystem<T> rain;
        do{
            G *= 2;
            System.out.println("G"+G);
            gs = new GravityLaw(G, D, dmm, G_scale);
            move.setIteraction(gs);
            rain = new RainSystem(set, move, pick);
        }while( MIN_FUSIONS - fusions(rain, MIN_FUSIONS+EPSILON_FUSIONS) > EPSILON_FUSIONS ); 
        double a = (G>2)?G/2:0;
        double b = 2*G;
        G = (a+b)/2;
        System.out.println("G*"+G);
        gs = new GravityLaw(G, D, dmm, G_scale);
        move.setIteraction(gs);
        rain = new RainSystem(set, move, pick);
        int fus = fusions(rain, MIN_FUSIONS+EPSILON_FUSIONS);
        while( G/b < 0.99 && Math.abs(fus-MIN_FUSIONS) > EPSILON_FUSIONS){
            if( fus < MIN_FUSIONS ){
                a = G;
            }else{
                b = G;
            }
            G = (a+b)/2;
            System.out.println("G"+G);
            gs = new GravityLaw(G, D, dmm, G_scale);
            move.setIteraction(gs);
            rain = new RainSystem(set, move, pick);
            fus = fusions(rain, MIN_FUSIONS+EPSILON_FUSIONS);
        }
        return G;
    }
    
}
