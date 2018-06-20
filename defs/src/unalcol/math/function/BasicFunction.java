package unalcol.math.function;

public abstract class BasicFunction<S,T> implements RunnableFunction<S,T>{
	protected S in;
	protected T out;
	
	@Override
	public void setInput(S in){ this.in=in; }

	@Override
	public void setOutput(T out) { this.out=out; }

	@Override
	public S input(){ return in; }

	@Override
	public T output(){ return out; }
}
