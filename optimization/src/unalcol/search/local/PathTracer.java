package unalcol.search.local;

import unalcol.tracer.BasicTracer;
import unalcol.types.object.tagged.Tagged; 

public class PathTracer<T> extends BasicTracer {
    public static final String PARENT = "parent";
    
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object caller, Object... obj){
		Tagged<T> parent = (Tagged<T>)obj[0];
		if( obj.length % 2 == 1 ){
			int k=1;		
			for( int i=k; i<obj.length; i+=2 ) ((Tagged<T>)obj[i+1]).setTag( PARENT, parent);	
		}
	}

	@Override
	public void clean(){}

	@Override
	public void close(){}

	@Override
	public Object get(){ return null; }	
}