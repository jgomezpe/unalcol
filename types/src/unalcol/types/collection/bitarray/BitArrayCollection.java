/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.bitarray;

import unalcol.types.collection.array.Array;

/**
 *
 * @author jgomez
 */
public class BitArrayCollection implements Array<Boolean>{
	protected BitArray array;
   
	public BitArrayCollection( boolean[] bits ){ array = new BitArray(bits); }

	@Override
	public Boolean get(int index) throws ArrayIndexOutOfBoundsException{ return array.get(index); }

	@Override
	public int size(){ return array.dimension(); }
}