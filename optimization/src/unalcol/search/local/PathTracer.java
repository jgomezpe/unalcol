package unalcol.search.local;

import unalcol.Tagged;
import unalcol.Thing;
import unalcol.tracer.Tracer; 

public class PathTracer<T> extends Thing implements Tracer<Tagged<T>> {
    public static final String PARENT = "parent";
    
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object... obj){
		Tagged<T> parent = (Tagged<T>)obj[0];
		if( obj.length % 2 == 1 ){
			int k=1;		
			for( int i=k; i<obj.length; i+=2 ){
			    ((Tagged<T>)obj[i+1]).set( PARENT, parent);
			}	
		}
	}

	@Override
	public void clean(){}

	@Override
	public void close(){}

	@Override
	public Object get(){ return null; }
	
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