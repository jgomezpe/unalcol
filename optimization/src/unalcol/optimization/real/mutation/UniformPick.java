/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class UniformPick implements PickComponents{
    protected double prob;
    protected RawGenerator g;
    
    public UniformPick(){
        this(-1.0);
    }
    
    public UniformPick( double prob ){
        this.prob = prob;
        g = new JavaGenerator();
    }
    
    @Override
    public int[] get( int DIMENSION ) {
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
        for( int i=0; i<indices.length; i++ ){
            indices[i] = v.get(i);
        }
        return indices;
    }    
}