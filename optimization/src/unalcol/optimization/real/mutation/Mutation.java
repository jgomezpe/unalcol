/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.optimization.real.mutation;

import unalcol.search.space.variation.ArityOne;

/**
 *
 * @author jgomezpe
 */
public abstract class Mutation implements ArityOne<double[]>{
    // Mutation definitions
    protected PickComponents components = null;
    protected int[] indices = new int[0];
    
    public Mutation(PickComponents components){
        this.components = components;
    }
    
    public Mutation(){}            
}