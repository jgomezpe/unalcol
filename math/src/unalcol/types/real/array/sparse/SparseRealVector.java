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
	protected int n;
    
	public SparseRealVector(int n){ this.n = n; }
     
	@Override
	public int size(){ return n; }
    
	@Override
	public boolean set(int i, Double x) throws ArrayIndexOutOfBoundsException {
		if( i<0 || i>=n ) throw new ArrayIndexOutOfBoundsException();
		return super.set(i, (Double)x);
	}
    
	@Override
	public Double get( int i ) throws ArrayIndexOutOfBoundsException {
		if( i<0 || i>=n ) throw new ArrayIndexOutOfBoundsException();
		Double x;
		try{ 
			x = super.get(i); 
			if( x==null ) x = 0.0;
		}catch( ArrayIndexOutOfBoundsException e ){ x=0.0; }
		return x;
	}
    
    public void removeZeroes( double epsilon ){
        for( int i=vector.size(); i>=0; i-- ) if( Math.abs(vector.get(i).value()) <= epsilon )  vector.remove(i);
    }
    
    protected void update( SortedVector<KeyValue<Integer,Double>> vector, int n ){
    	this.vector = vector;
    	this.n = n;
    }
}