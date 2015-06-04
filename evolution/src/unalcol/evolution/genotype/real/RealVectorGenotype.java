package unalcol.evolution.genotype.real;
import unalcol.evolution.Genotype;
//import unalcol.math.realvector.RandomVector;
import unalcol.types.real.array.DoubleArrayInit;

/**
 * <p>Title: RealVectorGenotype</p>
 * <p>Description: Real vector encoding</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class RealVectorGenotype  extends Genotype<double[]> {
  protected int n = 1;

  /**
   * Creates a RealVectorGenotype With the given RealVectorLimits
   */
  public RealVectorGenotype() {
  }

  /**
   * Creates a RealVectorGenotype With the given RealVectorLimits
   * @param n Dimension of the real vector
   */
  public RealVectorGenotype( int n ) {
      this.n = n;
  }

  /**
   * Creates a new genome of the given genotype
   * @return Object The new genome
   */
  public double[] get() {
    return DoubleArrayInit.random(n);
  }

  /**
   * Returns the number of genes in the individual's genome
   * @return Number of genes in the individual's genome
   */
  public int size(double[] genome) {
    return genome.length;
  }

  public Object owner(){
      return double[].class;
  }
}