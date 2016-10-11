package unalcol.search.local;

import unalcol.search.solution.Solution;
import unalcol.tracer.Tracer; 

public class PathTracer<T> extends Tracer {
    public static final String PARENT = "parent";
    
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object... obj) {
		// TODO Auto-generated method stub
		Solution<T> child = (Solution<T>)obj[0];
		if( obj.length % 2 == 1 ){
			int k=1;		
			for( int i=k; i<obj.length; i+=2 ){
			    child.set( (String)obj[i], obj[i+1]);
			}	
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void close() {
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return null;
	}
}