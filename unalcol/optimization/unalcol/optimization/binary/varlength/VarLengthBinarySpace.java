package unalcol.optimization.binary.varlength;

import unalcol.bit.Array;
import unalcol.random.raw.Generator;
import unalcol.search.space.Space;

public class VarLengthBinarySpace implements Space<Array> {
	protected int minLength;
	protected int maxVarGenes;
	protected int gene_size;
	
	public VarLengthBinarySpace( int minLength, int maxLength ){ this(minLength, maxLength, 1); }

	public VarLengthBinarySpace( int minLength, int maxLength, int gene_size ){
		this.minLength = minLength;
		this.gene_size = gene_size;
		this.maxVarGenes = (maxLength-minLength)/gene_size;
	}
	
	@Override
	public boolean feasible(Array x) {
		return minLength <= x.size() && x.size()<=minLength+maxVarGenes*gene_size;
	}

	@Override
	public double feasibility(Array x) {
		return feasible(x)?1:0;
	}

	@Override
	public Array repair(Array x) {
		int maxLength = minLength + maxVarGenes * gene_size;
		if( x.size() > maxLength ) return x.subBitArray(0,maxLength);
		if( x.size() < minLength ){
			Array x2 = new Array(minLength, true);
			try{ for( int i=0; i<minLength;i++)	x2.set(i,x.get(i)); }catch(Exception e){}
			return x2;
		}else return x;
	}
	
	
	@Override
	public Array pick() {
		return (maxVarGenes>0)?new Array(minLength+Generator.cast(this).integer(maxVarGenes*gene_size), true):new Array(minLength, true);
	}
}