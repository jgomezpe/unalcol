package unalcol.tracer;

public class CountTracer extends BasicTracer{
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