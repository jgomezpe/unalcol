package unalcol.optimization.blackbox;

import unalcol.search.space.ArityOne;
import unalcol.search.space.Space;

public class BlackBoxArityOne<T> extends ArityOne<T> {
	protected ArityOne<T> mutation;
	protected BlackBoxFunction<T> bb_function;
	protected int n;
	
	public BlackBoxArityOne( int n, ArityOne<T> mutation, BlackBoxFunction<T> bb_function ) {
		this.n = n;
		this.bb_function = bb_function;
		this.mutation = mutation;
	}

	@Override
	public T apply(T x) {
		T s = mutation.apply(x);
		double q = bb_function.apply(s);
//		if( Math.random() < 0.5 )
		for( int i=1; i<n; i++ ){
			T s2 = mutation.apply(x);
			double q2 = bb_function.apply(s2);
			if( q > q2 ){
				s = s2;
				q = q2;
			}
		}
		return s;
	}

	@Override
	public T apply(Space<T> space, T x) {
		//System.out.println("n="+n);
		//return mutation.apply(space, x);
		
		T s = mutation.apply(space, x);
		double q = bb_function.apply(s);
//		if( Math.random() < 0.5 )
		for( int i=1; i<n; i++ ){
			T s2 = mutation.apply(space,x);
			double q2 = bb_function.apply(s2);
			if( q < q2 ){
				s = s2;
				q = q2;
			}
		}
		return s;
	}

	@Override
	public void adapt( double productivity ){
		mutation.adapt(productivity);
	}
}