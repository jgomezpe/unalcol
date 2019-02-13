/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import unalcol.collection.keymap.KeyValue;
import unalcol.collection.sparse.SparseArray;
import unalcol.collection.vector.Sorted;

/**
 *
 * @author jgomez
 */
public class Vector extends SparseArray<Double>{
	protected double epsilon=1e-10;
	
	protected int n;
    
	public Vector(int n){ this.n = n; }
	
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
	public Double get( Integer i ){ try{ return super.get(i); }catch(Exception e){ return 0.0; } }
    
    public void removeZeroes(){ removeZeroes(epsilon); }
    
    public void removeZeroes( double epsilon ){
    	try{ for( int i=vector.size(); i>=0; i-- ) if( Math.abs(vector.get(i).value()) <= epsilon )  vector.remove(i); }catch(Exception e){}	
    }
    
    protected void update( Sorted<KeyValue<Integer,Double>> vector, int n ){
    	this.vector = vector;
    	this.n = n;
    }
}