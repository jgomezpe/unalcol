package unalcol.optimization.binary.mutation;

import unalcol.bit.Array;
import unalcol.integer.Rand;
import unalcol.random.util.Shuffle;
import unalcol.search.variation.Variation_1_1;

public class IntensityMutation implements Variation_1_1<Array>{

	protected Rand ri;
	
	public IntensityMutation(Rand ri) { this.ri = ri; }
	
	@Override
	public Array apply(Array gen) {
		gen = new Array(gen);
		int k = ri.next() % gen.size();
		Shuffle<Object> s = new Shuffle<Object>();
		int[] idx = s.apply(gen.size());
		
		for( int i=0; i<k; i++ ){ gen.not(idx[i]); }
		return gen;
	}
	
}
