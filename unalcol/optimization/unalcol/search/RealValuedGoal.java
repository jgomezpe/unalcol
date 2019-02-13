package unalcol.search;

public abstract class RealValuedGoal<T> extends Goal<T, Double>{
	public abstract boolean minimizing();
	public abstract void minimize( boolean minimize );
}