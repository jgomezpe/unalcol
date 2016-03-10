package unalcol.search.local;

import unalcol.search.variation.ParameterizedObject;
import unalcol.search.variation.SearchOperator;

public interface AdaptSearchOperatorParameters<P> {
	public P apply( P parameters, double current, double next );
	public default void apply( SearchOperator<?> operator, double current, double next ){
		if( operator instanceof ParameterizedObject ){
			@SuppressWarnings("unchecked")
			ParameterizedObject<P> pobject = (ParameterizedObject<P>)operator;
			pobject.setParameters(apply(pobject.getParameters(), current, next));
		}
	}
}
