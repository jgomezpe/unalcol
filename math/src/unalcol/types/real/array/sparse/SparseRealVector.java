/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import java.util.Iterator;

import unalcol.types.collection.sparse.vector.SparseElement;
import unalcol.types.collection.sparse.vector.SparseVector;
import unalcol.types.collection.vector.SortedVector;

/**
 *
 * @author jgomez
 */
public class SparseRealVector{
    protected SparseVector<Double> values;
    protected int n;
    
    public SparseRealVector(int n){
        this.n = n;
        values = new SparseVector<>();
    }
        
    public int dim(){
        return n;
    }
    
    public int size(){  return values.size(); }
    
    public boolean set(int i, double x){
        //if( i>=0 && i<n ){
            values.set(i, x);
            return true;
        //}
        //return false;
    }
    
    public double get( int i ){
        try{
            return values.get(i);
        }catch( ArrayIndexOutOfBoundsException e ){
            return 0.0;
        }
    }
    
    public Iterator<SparseElement<Double>> elements(){
        return values.sparse_elements();
    }
    
    
    public void removeZeroes( double epsilon ){
        int n = values.size();
        SortedVector<SparseElement<Double>> v = values.sparseVector();
        for( int i=n-1; i>=0; i-- ){
            if( Math.abs(v.get(i).value()) <= epsilon ){
                v.remove(i);
            }
        }
    }
}
