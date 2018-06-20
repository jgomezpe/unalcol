package unalcol.math.function;

public class FunctionWrap<S,T> extends BasicFunction<S, T>{
	protected Function<S, T> f;

	public FunctionWrap( Function<S, T> f ){ setFunction(f); }

	public void setFunction( Function<S, T> f ){ this.f = f; }

	public Function<S, T> function(){ return f; }
	
	@Override
	public T apply(S x){ return f.apply(x); }
}