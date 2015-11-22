package unalcol.data.clustergenerator;

import unalcol.types.collection.vector.*;
import unalcol.random.raw.RawGenerator;

/**
 * <p>Title: ClusterGenerator</p>
 * <p>Description: Generates data points that will define a cluster.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class ClusterGenerator {
  /**
   * The kernel of the cluster. In general is a wire shaped structure that defines
   * the kernel (possibly more dense zone) of the cluster
   */
  public Wire kernel = null;
  /**
   * Boundary of the cluster. It is a set of restrictions that determines which
   * points belong to the cluster
   */
  public Restriction boundary = null;
  /**
   * Describes the spatial distribution of the points around the kernel
   */
  public Distribution distribution = null;

  /**
   * Creates a cluster generator
   * @param _kernel The kernel of the cluster. In general is a wire shaped structure that defines
   * the kernel (possibly more dense zone) of the cluster
   * @param _boundary Boundary of the cluster. It is a set of restrictions that determines which
   * points belong to the cluster
   * @param _distribution Describes the spatial distribution of the points around the kernel
   */
  public ClusterGenerator( Wire _kernel, Restriction _boundary,
                           Distribution _distribution ) {
    kernel = _kernel;
    boundary = _boundary;
    distribution = _distribution;
  }

  /**
   * Generates a data source with noise
   * @param noise_size Number of noise points (uniform distributed)
   * @return A data source with noise
   */
  public static Vector<double[]> noise(int noise_size) {
	  double[] raw = RawGenerator.raw(ClusterGenerator.class, 2*noise_size);
	  Vector<double[]> v = new Vector<double[]>();
	  int k=0;
	  for (int i = 0; i < noise_size; i++) {
		  v.add( new double[]{raw[k], raw[k+1]});
		  k += 2;
	  }
	  return v;
  }

  /**
   * Generates a set of data points that belong to the cluster
   * @param cluster_size Number of points in the cluster
   * in the 2D space
   * @return  A ObjectSource that contains the points generated for the cluster
   */
  public Vector<double[]> generate (int cluster_size) {
    Vector<double[]> v = new Vector<double[]>();
    int trials = 0;
    while (v.size() < cluster_size && trials < cluster_size * 100) {
      double[] p = kernel.getPoint(distribution);
      if (boundary == null || boundary.satisfy(p)) {
        v.add(p);
      }
      trials++;
    }
    return v;
  }
}
