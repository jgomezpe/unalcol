package unalcol.search;

import unalcol.types.real.Abs;

public abstract class RealQualityGoal<T> extends DistanceToGoal<T,Double> {
    public RealQualityGoal( double goal_value ){ super( goal_value, new Abs()); }
}