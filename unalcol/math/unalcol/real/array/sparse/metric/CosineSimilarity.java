package unalcol.real.array.sparse.metric;


import unalcol.real.array.sparse.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jgomez
 */
public class CosineSimilarity extends NormalizedCosineSimilarity{
    @Override
    public double apply( Vector x , Vector y){
        return super.apply(x, y)/(prod.norm(x)*prod.norm(y));
    }
}
