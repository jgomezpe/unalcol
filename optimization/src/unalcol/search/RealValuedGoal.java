package unalcol.search;

public interface RealValuedGoal<T> extends Goal<T, Double>{
	public boolean minimizing();
	public void minimize( boolean minimize );
}