package unalcol.optimization.binary;

import unalcol.search.space.Space;
import unalcol.types.collection.bitarray.BitArray;

public class BinarySpace extends Space<BitArray> {
	protected int n;
	
	public BinarySpace( int n ){
		this.n = n; 
	}

	@Override
	public boolean feasible(BitArray x) {
		return x.size()==n;
	}

	@Override
	public double feasibility(BitArray x) {
		return feasible(x)?1:0;
	}

	@Override
	public BitArray repair(BitArray x) {
		if( x.size() != n ){
			if(x.size()>n){
				x = x.subBitArray(0,n);
			}else{
				x = new BitArray(n, true);
				for( int i=0; i<n;i++)
					x.set(i,x.get(i));
			}
		}
		return x;
	}

	@Override
	public BitArray get() {
		return new BitArray(n, true);
	}
}
