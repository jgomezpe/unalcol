package unalcol.optimization.binary.varlength;

import unalcol.random.raw.RawGenerator;
import unalcol.search.space.Space;
import unalcol.types.collection.bitarray.BitArray;

public class VarLengthBinarySpace extends Space<BitArray> {
	protected int minLength;
	protected int maxVarGenes;
	protected int gene_size;
	
	public VarLengthBinarySpace( int minLength, int maxLength ){
		this.minLength = minLength;
		this.maxVarGenes = maxLength - minLength;
		this.gene_size = 1;
	}

	public VarLengthBinarySpace( int minLength, int maxLength, int gene_size ){
		this.minLength = minLength;
		this.gene_size = gene_size;
		this.maxVarGenes = (maxLength-minLength)/gene_size;		
	}

	@Override
	public boolean feasible(BitArray x) {
		return minLength <= x.size() && x.size()<=minLength+maxVarGenes*gene_size;
	}

	@Override
	public double feasibility(BitArray x) {
		return feasible(x)?1:0;
	}

	@Override
	public BitArray repair(BitArray x) {
		int maxLength = minLength + maxVarGenes * gene_size;
		if( x.size() > maxLength ){
				x = x.subBitArray(0,maxLength);
		}else{
			if( x.size() < minLength )
			x = new BitArray(minLength, true);
			for( int i=0; i<minLength;i++)
				x.set(i,x.get(i));
		}
		return x;
	}

	@Override
	public BitArray pick() {
		return (maxVarGenes>0)?new BitArray(minLength+RawGenerator.integer(this, maxVarGenes*gene_size), true):new BitArray(minLength, true);
	}
}