/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection;

import unalcol.bit.Array;
import unalcol.collection.array.Immutable;
import unalcol.exception.ParamsException;

/**
 *
 * @author jgomez
 */
public class BitArray implements Immutable<Boolean>{
	protected Array array;
   
	public BitArray( boolean[] bits ){ array = new Array(bits); }

	@Override
	public Boolean get(Integer index) throws ParamsException{ return array.get(index); }

	@Override
	public int size(){ return array.dimension(); }

	@Override
	public Object[] toArray(){
		Boolean[] a = new Boolean[array.size()];
		try{ for( int i=0; i<a.length; i++ ) a[i] = array.get(i); }catch(Exception e){}
		return a; 
	}
}