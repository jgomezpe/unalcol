/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.sparse.SparseArray;
import unalcol.types.collection.vector.SortedVector;

/**
 *
 * @author jgomez
 */
public class SparseRealVector extends SparseArray<Double>{
	protected double epsilon=1e-10;
	
	protected int n;
    
	public SparseRealVector(int n){ this.n = n; }
	
	public void setEpsilon( double epsilon ){ this.epsilon = epsilon; }
     
	@Override
	public int size(){ return n; }
    
	@Override
	public boolean set(Integer i, Double x){
		if( isZero(x) ){ return super.remove(i); }
		return super.set(i, (Double)x);
	}
	
	public boolean isZero( Double x ){ return x==null || Math.abs(x) <= epsilon; }
    
	@Override
	public Double get( Integer i ){
		Double x = super.get(i); 
		if( x==null ) x = 0.0;
		return x;
	}
    
    public void removeZeroes(){ removeZeroes(epsilon); }
    
    public void removeZeroes( double epsilon ){
        for( int i=vector.size(); i>=0; i-- ) if( Math.abs(vector.get(i).value()) <= epsilon )  vector.remove(i);
    }
    
    protected void update( SortedVector<KeyValue<Integer,Double>> vector, int n ){
    	this.vector = vector;
    	this.n = n;
    }
}