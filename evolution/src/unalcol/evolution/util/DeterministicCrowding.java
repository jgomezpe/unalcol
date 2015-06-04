package unalcol.evolution.util;

import unalcol.optimization.replacement.Replacement;
import unalcol.evolution.*;
import unalcol.math.metric.QuasiMetric;
import unalcol.types.collection.vector.*;
import unalcol.clone.*;
import unalcol.optimization.solution.Solution;

/**
 * <p>Title: DeterministicCrowding</p>
 * <p>Description: The Deterministic Crowding Approach proposed by Mahfoud for niching</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * <p>Company: Prion</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */

public class DeterministicCrowding<T> extends Replacement<T>{
  /**
   * Distance between individuals
   */
  QuasiMetric metric = null;

  /**
   * Creates a Simple Genetic Algorithm Transformation function, with the given
   * selection scheme, and simple crossover, and simple gen mutation operators. The
   * operators have the given probability to be applied
   * @param _metric Distance between individuals
   */
  public DeterministicCrowding( QuasiMetric _metric ) {
    metric = IndividualMetric.generate( _metric);
  }

  @Override
  public Vector<Solution<T>> apply( Vector<Solution<T>> parent, Vector<Solution<T>> offspring){
      try{
          Vector<Solution<T>> buffer = new Vector<>();
          for( int i=0; i<parent.size(); i+=2 ){
            Solution<T> P1, P2, C1, C2;
            P1 = (Solution<T>) parent.get(i);
            P2 = (Solution<T>) parent.get(i+1);
            C1 = (Solution<T>) offspring.get(i);
            C2 = (Individual) offspring.get(i+1);
            if (metric.apply(P1, C1) + metric.apply(P2, C2) <=
                metric.apply(P1, C2) + metric.apply(P2, C1)) {
                Solution<T> temp = C1;
                C1 = C2;
                C2 = temp;
            }
            if (C1.value() < P1.value()) {
                C1 = (Individual) Clone.get(P1);
            }
            if (C2.value() < P2.value()) {
                C2 = (Individual) Clone.get(P2);
            }
            buffer.add(C1);
            buffer.add(C2);
          }
          return buffer;
      }catch( Exception e ){
          e.printStackTrace();
      }
      return null;
  }
}
