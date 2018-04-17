package unalcol.tracer;

import unalcol.services.MicroService;
import unalcol.types.collection.vector.Vector;

public class VectorTracer<T> extends MicroService<T> implements Tracer<T> {
	/**
	 * Traced information
	 */
	private Vector<Object[]> object = new Vector<Object[]>();

	/**
	 * Creates a new SingleResultTracer
	 */
	public VectorTracer(){}

	/**
	 * Replaces the traced information with a new one
	 * @param obj Traced information
	 */
	public void add(Object... obj){ object.add(obj); }

	/**
	 * Returns the traced information
	 * @return A single object representing the traced information
	 */
	public Object get(){ return object; }

	/**
	 * Cleans the traced information
	 */
	public void clean(){ object.clear(); }

	/**
	 * Closes the tracer (does nothing)
	 */
	public void close() {};

	protected boolean isTracing=false;

	@Override
	public boolean tracing() { return isTracing; }

	@Override
	public boolean start() {
		boolean old = isTracing;
		isTracing=true;
		return old;
	}

	@Override
	public boolean stop() {
		boolean old = isTracing;
		isTracing=false;
		return old;
	}    
}