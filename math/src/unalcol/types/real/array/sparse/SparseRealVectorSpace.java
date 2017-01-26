/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.util.Iterator;

import unalcol.clone.Clone;
import unalcol.math.algebra.VectorSpace;
import unalcol.types.collection.sparse.vector.SparseElement;
import unalcol.types.collection.sparse.vector.SparseElementOrder;
import unalcol.types.collection.sparse.vector.SparseVector;
import unalcol.types.collection.vector.SortedVector;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorSpace implements VectorSpace<SparseRealVector> {
    protected SparseElementOrder<Double> order = new SparseElementOrder<Double>();

    @Override
    public SparseRealVector identity( SparseRealVector x ){
        return new SparseRealVector(x.dim());
    }

    @Override
    public SparseRealVector fastInverse( SparseRealVector x ){
        Iterator<SparseElement<Double>> iter = x.elements();
        while( iter.hasNext() ){
            SparseElement<Double> el = 
                    (SparseElement<Double>)iter.next();
            el.setValue(-el.value());
        }
        return x;
    }
    
    protected SparseElement<Double> element( Iterator<SparseElement<Double>> iter ){
        if( iter.hasNext() ){
            return iter.next();
        }
        return null;
    }

    protected int indexCounter(SparseRealVector x, SparseRealVector y) {
        int counter = 0;
        Iterator<SparseElement<Double>> iter_x = x.elements();
        Iterator<SparseElement<Double>> iter_y = y.elements();
        SparseElement<Double> elem_x = element(iter_x);
        SparseElement<Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.index() < elem_y.index() ){
                counter++;
                elem_x = element(iter_x);                
            }else{
                if(elem_x.index() > elem_y.index() ){
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
    @Override
    public SparseRealVector fastPlus(SparseRealVector x, SparseRealVector y) {
        int n = Math.max(x.values.size(),y.values.size()); //  indexCounter(x, y);
        @SuppressWarnings("unchecked")
		SparseElement<Double>[] elements = new SparseElement[n];
        Vector<SparseElement<Double>> v = new Vector<SparseElement<Double>>(elements,0);
        Iterator<SparseElement<Double>> iter_x = x.elements();
        Iterator<SparseElement<Double>> iter_y = y.elements();
        SparseElement<Double> elem_x = element(iter_x);
        SparseElement<Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.index() < elem_y.index() ){
                v.add(elem_x);
                elem_x = element(iter_x);
            }else{
                if(elem_x.index() > elem_y.index() ){
                    v.add(elem_y.clone());
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
            v.add(elem_y.clone());
            elem_y = element(iter_y);
        }
        SortedVector<SparseElement<Double>> zv = new SortedVector<SparseElement<Double>>(v, order);
        x.values.clear();
        x.values = new SparseVector<Double>(zv);
        return x;
    }
    

    /**
     * Subtracts the second vector from the first vector.
     * The subtraction process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @Override
    public SparseRealVector fastMinus(SparseRealVector x, SparseRealVector y) {
        int n = Math.max(x.values.size(),y.values.size()); //  indexCounter(x, y);
        @SuppressWarnings("unchecked")
		SparseElement<Double>[] elements = new SparseElement[n];
        Vector<SparseElement<Double>> v = new Vector<SparseElement<Double>>(elements,0);
        Iterator<SparseElement<Double>> iter_x = x.elements();
        Iterator<SparseElement<Double>> iter_y = y.elements();
        SparseElement<Double> elem_x = element(iter_x);
        SparseElement<Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.index() < elem_y.index() ){
                v.add(elem_x);
                elem_x = element(iter_x);
            }else{
                if(elem_x.index() > elem_y.index() ){
                    elem_y = elem_y.clone();
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
            elem_y = elem_y.clone();
            elem_y.setValue(-elem_y.value());
            v.add(elem_y);
            elem_y = element(iter_y);
        }
        SortedVector<SparseElement<Double>> zv = new SortedVector<SparseElement<Double>>(v, order);
        x.values.clear();
        x.values = new SparseVector<Double>(zv);
        return x;
    }
    
	/**
	 * Multiplies a vector by an scalar.
	 * @param y The vector
	 * @param x The scalar
	 */
  @Override
  public SparseRealVector fastMultiply(SparseRealVector y, double x) {
    if( x==0.0 ){
        y.values.clear();      
    }else{  
        Iterator<SparseElement<Double>> iter = y.elements();
        SparseElement<Double> elem;
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
  public SparseRealVector fastDivide(SparseRealVector y, double x) {
    Iterator<SparseElement<Double>> iter = y.elements();
    SparseElement<Double> elem;
    while( iter.hasNext() ){
        elem = iter.next();
        elem.setValue(elem.value()/x);
    }
    return y;
  }

    @Override
    public SparseRealVector inverse(SparseRealVector x) {
        return fastInverse((SparseRealVector)Clone.create(x));
    }

    @Override
    public SparseRealVector minus(SparseRealVector one, SparseRealVector two) {
        return fastMinus((SparseRealVector)Clone.create(one), two);
    }

    @Override
    public SparseRealVector plus(SparseRealVector one, SparseRealVector two) {
        return fastPlus((SparseRealVector)Clone.create(one), two);
    }

    @Override
    public SparseRealVector divide(SparseRealVector one, double x) {
        return fastDivide((SparseRealVector)Clone.create(one), x);
    }

    @Override
    public SparseRealVector multiply(SparseRealVector one, double x) {
        return fastMultiply((SparseRealVector)Clone.create(one), x);
    }

    
}

