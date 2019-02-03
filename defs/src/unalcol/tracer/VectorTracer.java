package unalcol.tracer;

import unalcol.types.collection.vector.Vector;

public class VectorTracer extends BasicTracer {
	/**
	 * Traced information
	 */
	private Vector<Object[]> object = new Vector<Object[]>();
	
	private int each=1;
	private int count = 0;

	/**
	 * Creates a new SingleResultTracer
	 */
	public VectorTracer(){}

	/**
	 * Creates a new SingleResultTracer
	 */
	public VectorTracer( int each ){ this.each=each; }

	/**
	 * Replaces the traced information with a new one
	 * @param obj Traced information
	 */
	public void add(Object caller, Object... obj){
		count = (count+1)%each;
		if( count==0 ) object.add(obj);
	}

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
	
	@Override
	public String toString(){ return "VectorTracer"; }	
}