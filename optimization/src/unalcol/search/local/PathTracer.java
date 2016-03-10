package unalcol.search.local;

import unalcol.search.solution.Solution;
import unalcol.tracer.Tracer; 

public class PathTracer<T> extends Tracer {
	
	public void add( Solution<T> parent, Solution<T> child ){
		child.set(PathTracer.class.getName(), parent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object... obj) {
		// TODO Auto-generated method stub
		Solution<T> parent = (Solution<T>)obj[0];
		if( obj.length > 1 ){
			int k=1;		
			if( obj.length==2 && obj[1] instanceof Solution[] ){
				k=0;
				obj = (Solution<T>[])obj[1];
			}	
			for( int i=k; i<obj.length; i++ )
				if( parent != obj[i] )
					add( parent, (Solution<T>)obj[i]);
		}else add( parent, parent );
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