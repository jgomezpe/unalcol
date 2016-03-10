package unalcol.search.variation;

public interface ParameterizedObject<P>{
	public void setParameters( P parameters );
	public P getParameters();
}