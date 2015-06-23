package unalcol.evolution.genotype.binary.varlength;
import unalcol.evolution.genotype.binary.*;
import unalcol.optimization.binary.BinarySpace;
import unalcol.random.integer.*;
import unalcol.search.space.Space;
import unalcol.types.collection.bitarray.BitArray;

/**
 * <p>Title:  BinaryGenome</p>
 * <p>Description: Interface for getting the bitarray from an Individual</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class VariableLengthBinaryGenotype extends BinarySpace {
  /**
   * Maximum number of genes
   */
  protected int max_n;
  /**
   * Delta length
   */
  protected int gene_size;
  /**
   * The final limit of the random number generator range
   */
  protected int max_delta;
  /**
   * Generator of new genes
   */
  protected UniformIntegerGenerator extra_genes;

  public VariableLengthBinaryGenotype(int min, int max, int gene_size) {
    super(min);
    this.gene_size = gene_size;
    this.max_n = max;
    extra_genes = new UniformIntegerGenerator(max_delta);
    max_length = length + max_delta * delta_length;
  }

	@Override
	public boolean feasible(BitArray x) {
		return n<=x.size() && x.size()<=max_n;
	}

	@Override
	public double feasibility(BitArray x) {
		return feasible(x)?1:0;
	}

	@Override
	public BitArray repair(BitArray x) {
		if( !feasible(x) ){
			if(x.size()>max_n){
				x = x.subBitArray(0,max_n);
			}else{
				x = new BitArray(n, true);
				for( int i=0; i<n;i++)
					x.set(i,x.get(i));
			}
		}
		return x;
	}

 
  /**
   * Creates a new genome of the given genotype
   * @return Object The new genome
   */
	@Override
  public BitArray get() {
    int n = extra_genes.next();
    return new BitArray(length + n * delta_length, true);
  }


	/**
   * Returns lenght
   * @return The lenght
   */
  public int getMinLength() { return length; }
  /**
   * Returns Max_Lenght
   * @return The maximum number of genes
   */
  public int getMaxLength() { return max_length; }
  /**
   * Returns DeltaLength
   * @return DeltaLenght
   */
  public int getDeltaLength() { return delta_length; }
  /**
   * Returns MaxDelta
   * @return The final limit of the random number generator range
   */
  public int getMaxDelta() { return max_delta; }
}
