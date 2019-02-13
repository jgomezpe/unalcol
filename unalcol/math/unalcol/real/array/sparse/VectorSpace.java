/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import java.util.Iterator;

import unalcol.clone.Cloneable;
import unalcol.collection.keymap.KeyOrder;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.vector.Sorted;
import unalcol.integer.Order;

/**
 *
 * @author jgomez
 */
public class VectorSpace implements unalcol.math.algebra.VectorSpace<Vector> {
    @Override
    public Vector identity( Vector x ){
        return new Vector(x.size());
    }

    @Override
    public Vector fastInverse( Vector x ){
        Iterator<KeyValue<Integer,Double>> iter = x.pairs().iterator();
        while( iter.hasNext() ){
            KeyValue<Integer,Double> el = iter.next();
            el.setValue(-el.value());
        }
        return x;
    }
    
    protected KeyValue<Integer,Double> element( Iterator<KeyValue<Integer,Double>> iter ){
        if( iter.hasNext() ) return iter.next();
        return null;
    }

    protected int indexCounter(Vector x, Vector y) {
        int counter = 0;
        Iterator<KeyValue<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<KeyValue<Integer,Double>> iter_y = y.pairs().iterator();
        KeyValue<Integer,Double> elem_x = element(iter_x);
        KeyValue<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.key() < elem_y.key() ){
                counter++;
                elem_x = element(iter_x);                
            }else{
                if(elem_x.key() > elem_y.key() ){
                    counter++;
                    elem_y = element(iter_y);
                }else{
                    counter++;
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        while( elem_x != null ){
            counter++;
            elem_x = element(iter_x);
        }
        while( elem_y != null ){
            counter++;
            elem_y = element(iter_y);
        }
        return counter;
    }
       
    /**
     * Adds the second vector to the first vector.
     * The addition process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public Vector fastPlus(Vector x, Vector y) {
    	int n = Math.max(x.size(), y.size());
        unalcol.collection.Vector<KeyValue<Integer,Double>> v = new unalcol.collection.Vector<KeyValue<Integer,Double>>();
        Iterator<KeyValue<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<KeyValue<Integer,Double>> iter_y = y.pairs().iterator();
        KeyValue<Integer,Double> elem_x = element(iter_x);
        KeyValue<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.key() < elem_y.key() ){
                v.add(elem_x);
                elem_x = element(iter_x);
            }else{
                if(elem_x.key() > elem_y.key() ){
                    v.add((KeyValue<Integer,Double>)Cloneable.cast(elem_y).clone());
                    elem_y = element(iter_y);
                }else{
                    double d = elem_x.value()+elem_y.value();
                    if( d != 0.0 ){
                        elem_x.setValue(d);
                        v.add(elem_x);
                    }
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        while( elem_x != null ){
            v.add(elem_x);
            elem_x = element(iter_x);
        }
        while( elem_y != null ){
            v.add((KeyValue<Integer,Double>)Cloneable.cast(elem_y).clone());
            elem_y = element(iter_y);
        }
        Sorted<KeyValue<Integer,Double>> zv = new Sorted<KeyValue<Integer,Double>>(v, new KeyOrder<Integer,Double>( new Order()) );
        x.update(zv,n);
        return x;
    }
    

    /**
     * Subtracts the second vector from the first vector.
     * The subtraction process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public Vector fastMinus(Vector x, Vector y) {
        int n = Math.max(x.size(),y.size()); //  indexCounter(x, y);
        unalcol.collection.Vector<KeyValue<Integer,Double>> v = new unalcol.collection.Vector<KeyValue<Integer,Double>>();
        Iterator<KeyValue<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<KeyValue<Integer,Double>> iter_y = y.pairs().iterator();
        KeyValue<Integer,Double> elem_x = element(iter_x);
        KeyValue<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.key() < elem_y.key() ){
                v.add(elem_x);
                elem_x = element(iter_x);
            }else{
                if(elem_x.key() > elem_y.key() ){
                    elem_y = (KeyValue<Integer,Double>)Cloneable.cast(elem_y).clone();
                    elem_y.setValue(-elem_y.value());
                    v.add(elem_y);                    
                    elem_y = element(iter_y);
                }else{
                    double d = elem_x.value()-elem_y.value();
                    if( d != 0.0 ){
                        elem_x.setValue(d);
                        v.add(elem_x);
                    }
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        while( elem_x != null ){
            v.add(elem_x);
            elem_x = element(iter_x);
        }
        while( elem_y != null ){
            elem_y = (KeyValue<Integer,Double>)Cloneable.cast(elem_y).clone();
            elem_y.setValue(-elem_y.value());
            v.add(elem_y);
            elem_y = element(iter_y);
        }
        Sorted<KeyValue<Integer,Double>> zv = new Sorted<KeyValue<Integer,Double>>(v, new KeyOrder<Integer,Double>( new Order()) );
        x.update(zv,n);
        return x;
    }
    
	/**
	 * Multiplies a vector by an scalar.
	 * @param y The vector
	 * @param x The scalar
	 */
	@Override
	public Vector fastMultiply(Vector y, double x) {
		if( x==0.0 ) y.clear();
		else{  
			Iterator<KeyValue<Integer,Double>> iter = y.pairs().iterator();
			KeyValue<Integer,Double> elem;
			while( iter.hasNext() ){
				elem = iter.next();
				elem.setValue(x * elem.value());
			}
		}            
		return y;
	}
	
	/**
	 * Divides a vector by an scalar.
	 * @param one The vector
	 * @param x The scalar
	 */
	@Override
	public Vector fastDivide(Vector y, double x){ return fastMultiply(y, 1.0/x); }
	
	protected Vector create(Vector x){ return (Vector)Cloneable.cast(x).clone(); }

    @Override
    public Vector inverse(Vector x) {
        return fastInverse(create(x));
    }

    @Override
    public Vector minus(Vector one, Vector two) {
        return fastMinus(create(one), two);
    }

    @Override
    public Vector plus(Vector one, Vector two) {
        return fastPlus(create(one), two);
    }

    @Override
    public Vector divide(Vector one, double x) {
        return fastDivide(create(one), x);
    }

    @Override
    public Vector multiply(Vector one, double x) {
        return fastMultiply(create(one), x);
    }
}