/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.util.Iterator;

import unalcol.clone.Cloneable;
import unalcol.math.algebra.VectorSpace;
import unalcol.types.collection.keymap.KeyOrder;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.vector.SortedVector;
import unalcol.types.collection.vector.Vector;
import unalcol.types.integer.IntegerOrder;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorSpace implements VectorSpace<SparseRealVector> {
    @Override
    public SparseRealVector identity( SparseRealVector x ){
        return new SparseRealVector(x.size());
    }

    @Override
    public SparseRealVector fastInverse( SparseRealVector x ){
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

    protected int indexCounter(SparseRealVector x, SparseRealVector y) {
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
    public SparseRealVector fastPlus(SparseRealVector x, SparseRealVector y) {
    	int n = Math.max(x.size(), y.size());
        Vector<KeyValue<Integer,Double>> v = new Vector<KeyValue<Integer,Double>>();
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
        SortedVector<KeyValue<Integer,Double>> zv = new SortedVector<KeyValue<Integer,Double>>(v, new KeyOrder<Integer,Double>( new IntegerOrder()) );
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
    public SparseRealVector fastMinus(SparseRealVector x, SparseRealVector y) {
        int n = Math.max(x.size(),y.size()); //  indexCounter(x, y);
        Vector<KeyValue<Integer,Double>> v = new Vector<KeyValue<Integer,Double>>();
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
        SortedVector<KeyValue<Integer,Double>> zv = new SortedVector<KeyValue<Integer,Double>>(v, new KeyOrder<Integer,Double>( new IntegerOrder()) );
        x.update(zv,n);
        return x;
    }
    
	/**
	 * Multiplies a vector by an scalar.
	 * @param y The vector
	 * @param x The scalar
	 */
	@Override
	public SparseRealVector fastMultiply(SparseRealVector y, double x) {
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
	public SparseRealVector fastDivide(SparseRealVector y, double x){ return fastMultiply(y, 1.0/x); }
	
	protected SparseRealVector create(SparseRealVector x){ return (SparseRealVector)Cloneable.cast(x).clone(); }

    @Override
    public SparseRealVector inverse(SparseRealVector x) {
        return fastInverse(create(x));
    }

    @Override
    public SparseRealVector minus(SparseRealVector one, SparseRealVector two) {
        return fastMinus(create(one), two);
    }

    @Override
    public SparseRealVector plus(SparseRealVector one, SparseRealVector two) {
        return fastPlus(create(one), two);
    }

    @Override
    public SparseRealVector divide(SparseRealVector one, double x) {
        return fastDivide(create(one), x);
    }

    @Override
    public SparseRealVector multiply(SparseRealVector one, double x) {
        return fastMultiply(create(one), x);
    }
}