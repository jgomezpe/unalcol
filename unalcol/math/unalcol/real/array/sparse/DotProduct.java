/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import java.util.Iterator;

import unalcol.collection.keymap.KeyValue;;

/**
 *
 * @author jgomez
 */
public class DotProduct {
    protected KeyValue<Integer,Double> element( Iterator<KeyValue<Integer,Double>> iter ){
        if( iter.hasNext() ){
            return iter.next();
        }
        return null;
    }

    public double apply(Vector x, Vector y){
        double prod = 0.0;
        Iterator<KeyValue<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<KeyValue<Integer,Double>> iter_y = y.pairs().iterator();
        KeyValue<Integer,Double> elem_x = element(iter_x);
        KeyValue<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.key() < elem_y.key() ){
                elem_x = element(iter_x);                
            }else{
                if(elem_x.key() > elem_y.key() ){
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
    
    public double sqr_norm(Vector x){
        double prod = 0.0;
        for( Double y:x) prod += y*y;
        return prod;
    }
    
    public double norm(Vector x){
        return Math.sqrt(sqr_norm(x));
    }
}
