/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.util.Iterator;

import unalcol.types.collection.sparse.vector.SparseElement;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorDotProduct {
    protected SparseElement<Double> element( Iterator<SparseElement<Double>> iter ){
        if( iter.hasNext() ){
            return iter.next();
        }
        return null;
    }

    public double apply(SparseRealVector x, SparseRealVector y){
        double prod = 0.0;
        Iterator<SparseElement<Double>> iter_x = x.elements();
        Iterator<SparseElement<Double>> iter_y = y.elements();
        SparseElement<Double> elem_x = element(iter_x);
        SparseElement<Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.index() < elem_y.index() ){
                elem_x = element(iter_x);                
            }else{
                if(elem_x.index() > elem_y.index() ){
                    elem_y = element(iter_y);
                }else{
                    prod += elem_y.value() * elem_x.value();
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        return prod;
    }
    
    public double sqr_norm(SparseRealVector x){
        double prod = 0.0;
        Iterator<SparseElement<Double>> iter = x.elements();
        SparseElement<Double> elem;
        while( iter.hasNext() ){
            elem = iter.next();
            prod += elem.value() * elem.value();
        }
        return prod;
    }
    
    public double norm(SparseRealVector x){
        return Math.sqrt(sqr_norm(x));
    }
}
