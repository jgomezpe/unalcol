package unalcol.evolution.util;

import unalcol.evolution.*;
import unalcol.math.metric.QuasiMetric;

/**
 * <p>Title: IndividualMetric</p>
 * <p>Description: To get the distance between to individuals</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class IndividualMetric<G,P> implements QuasiMetric<Individual<G,P>> {
  /**
   * A QuasiMetric
   */
  protected QuasiMetric<P> metric;
  /**
   * Default constructor
   * @param _metric The Quasimetric
   */
  public IndividualMetric(QuasiMetric<P> _metric) {
    metric = _metric;
  }

  /**
   * Distance (Implement this unalcol.util.quasimetric.QuasiMetric method)
   * @param one First Object
   * @param two Second Object
   * @return double
   */
  public double apply(Individual<G,P> one, Individual<G,P> two) {
    return metric.apply(one.get(), two.get());
  }
  /**
   * Generates a IndividualMetric
   * @param metric The QuasiMetric
   * @return A IndividualMetrics
   */
  public static QuasiMetric generate( QuasiMetric metric) {
    if(metric instanceof IndividualMetric) {
      return metric;
    } else {
      return new IndividualMetric(metric);
    }
  }
}
