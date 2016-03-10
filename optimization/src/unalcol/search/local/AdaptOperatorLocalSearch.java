package unalcol.search.local;

import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.search.variation.ArityOneSearchOperator;
import unalcol.tracer.Tracer;

public class AdaptOperatorLocalSearch<T,P> extends VariationReplaceLocalSearch<T>{
    protected AdaptSearchOperatorParameters<P> adapt;
    
    
    public AdaptOperatorLocalSearch( ArityOneSearchOperator<T> variation,
    								 AdaptSearchOperatorParameters<P> adapt, 
    								 Replacement<T> replace ){
        super( variation, replace );
        this.adapt = adapt;
    }
    
    @Override
    public Solution<T> apply(Solution<T> x, Space<T> space){
        // Check if non stationary
        Double fx = (Double)x.info(Goal.class.getName());        
        @SuppressWarnings("unchecked")
		Solution<T> y = variation.apply(space, x)[0];
        y.set(Goal.class.getName(), x.data(Goal.class.getName()));
        Double fy = (Double)y.info(Goal.class.getName());
        if( adapt != null )	adapt.apply(variation, fx, fy);
        Solution<T> z = replace.apply(x, y);
        Tracer.trace(Solution.class, x, z);
        return z;
    }    

}
