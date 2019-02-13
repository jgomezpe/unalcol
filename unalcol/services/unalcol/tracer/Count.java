package unalcol.tracer;

public class Count extends Tracer{
	protected int count=0;
	
	@Override
	public void add(Object caller, Object... obj) { count++; }

	@Override
	public void clean() { count=0; }

	@Override
	public void close() {}

	@Override
	public Object get() { return count; }
}