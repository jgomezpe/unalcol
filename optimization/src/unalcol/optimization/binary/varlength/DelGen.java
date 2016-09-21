package unalcol.optimization.binary.varlength;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.clone.*;
import unalcol.random.raw.RawGenerator;
import unalcol.search.variation.ParameterizedObject;
import unalcol.search.variation.Variation_1_1;


/**
 * <p>Title: DelGen</p>
 * <p>Description: The gene deletion operator.  Deletes a gene in the genome</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class DelGen extends Variation_1_1<BitArray> implements ParameterizedObject<int[]> {
  /**
   * If the last gene is going to be deleted or one randomly selected
   */
  protected boolean del_last_gene = true;

  protected int gene_size;
  protected int min_length;
  protected int max_length;


  public DelGen(int gene_size, int min_length, int max_length) {
      this.gene_size = gene_size;
      this.min_length = min_length;
      this.max_length = max_length;
  }


  /**
   * Constructor: create a deletion gene operator that deletes a gene of a genome
   * @param gene_size Number of bits defining a gene
   * @param min_length Minimum number of genes in the chromosome
   * @param max_length Maximun number of genes in the chromosome
   * @param del_last_gene Determines if the gene to be deleted is the last in
   * the genome or not. A true value indicates that the last gene is deleted.
   * A false value indiciates that a gene is randomly selected and deleted
   */
  public DelGen(int gene_size, int min_length, int max_length, boolean del_last_gene) {
      this(gene_size, min_length, max_length);
      this.del_last_gene = del_last_gene;
  }

  /**
   * Delete from the given genome the last gene
   * @param gen Genome to be modified
   */
  public BitArray apply(BitArray gen) {
      try{
          BitArray genome = (BitArray) Clone.create(gen);
          if (genome.size() > min_length + gene_size) {
              if (del_last_gene) {
                  genome.del(gene_size);
              } else {
                  int size = (genome.size()-min_length)/gene_size;
                  int k = RawGenerator.get(this).integer(size + 1);
                  BitArray right = genome.subBitArray(min_length + (k + 1) * gene_size);
                  genome.del((size - k) * gene_size);
                  genome.add(right);
              }
          }
          return genome;         
      }catch( Exception e ){
      }
      return null;
 }

  @Override
  public void setParameters(int[] parameters) {
	// TODO Auto-generated method stub
	gene_size = parameters[0];
	min_length = parameters[1];
	max_length = parameters[2];
  }

  @Override
  public int[] getParameters() {
	// TODO Auto-generated method stub
	return new int[]{gene_size, min_length, max_length};
  }
}
