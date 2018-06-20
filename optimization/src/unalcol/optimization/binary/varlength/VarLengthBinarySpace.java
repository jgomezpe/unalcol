package unalcol.optimization.binary.varlength;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.search.space.Space;
import unalcol.types.collection.bitarray.BitArray;

public class VarLengthBinarySpace implements Space<BitArray> {
	protected int minLength;
	protected int maxVarGenes;
	protected int gene_size;
	protected RawGenerator raw_g = new JavaGenerator();
	
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
	
	public void setRaw( RawGenerator raw_g ){ this.raw_g = raw_g; }

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
	
	public void setraw(RawGenerator raw){ this.raw_g = raw; }
	
	@Override
	public BitArray pick() {
		return (maxVarGenes>0)?new BitArray(minLength+raw_g.integer(maxVarGenes*gene_size), true):new BitArray(minLength, true);
	}
}