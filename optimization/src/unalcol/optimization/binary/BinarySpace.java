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
		return true;
	}

	@Override
	public double feasibility(BitArray x) {
		return 1;
	}

	@Override
	public BitArray repair(BitArray x) {
		return x;
	}

	@Override
	public BitArray get() {
		return new BitArray(n, true);
	}

}
