/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.bitarray;

import unalcol.types.collection.array.ImmutableArray;

/**
 *
 * @author jgomez
 */
public class BitArrayCollection implements ImmutableArray<Boolean>{
	protected BitArray array;
   
	public BitArrayCollection( boolean[] bits ){ array = new BitArray(bits); }

	@Override
	public Boolean get(Integer index){ return array.get(index); }

	@Override
	public int size(){ return array.dimension(); }

	@Override
	public Object[] toArray(){
		Boolean[] a = new Boolean[array.size()];
		for( int i=0; i<a.length; i++ ) a[i] = array.get(i);
		return a; 
	}
}