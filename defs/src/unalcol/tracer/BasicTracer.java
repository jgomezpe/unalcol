package unalcol.tracer;

public abstract class BasicTracer implements Tracer{
	protected boolean isTracing=false;
	
	@Override
	public boolean tracing() { return isTracing; }

	@Override
	public void start(){ isTracing=true; }

	@Override
	public void stop(){ isTracing=false; }    
}
