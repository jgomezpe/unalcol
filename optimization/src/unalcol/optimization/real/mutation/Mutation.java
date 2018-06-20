/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.optimization.real.mutation;

import unalcol.search.variation.Variation_1_1;

/**
 *
 * @author jgomezpe
 */
public abstract class Mutation implements Variation_1_1<double[]>{
    // Mutation definitions
    protected PickComponents components = null;
    protected int[] indices = new int[0];
    
    public Mutation(PickComponents components){
        this.components = components;
    }
    
    public Mutation(){}            
}