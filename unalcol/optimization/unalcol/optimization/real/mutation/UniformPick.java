/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.collection.Vector;
import unalcol.random.raw.Generator;

/**
 *
 * @author jgomez
 */
public class UniformPick implements PickComponents{
    protected double prob;
    
    public UniformPick(){
        this(-1.0);
    }
    
    public UniformPick( double prob ){ this.prob = prob; }
    
    @Override
    public int[] get( int DIMENSION ) {
    	Generator g = Generator.cast(this);
        double tprob = prob;
        if( tprob <= 0.0 ){
            tprob = 1.0 / DIMENSION;
        }
        tprob = 1.0 - tprob;
        Vector<Integer> v = new Vector<Integer>();
        for( int i=0; i<DIMENSION; i++ ){
            if(g.bool(tprob)){
                v.add(i);
            }                
        }
        int[] indices = new int[v.size()];
        try{ for( int i=0; i<indices.length; i++ ) indices[i] = v.get(i); }catch(Exception e){}
        return indices;
    }    
}