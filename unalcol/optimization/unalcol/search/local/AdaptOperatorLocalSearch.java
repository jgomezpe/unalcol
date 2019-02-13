package unalcol.search.local;

import unalcol.search.Goal;
import unalcol.search.replacement.GoalBasedReplacement;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.object.Tagged;

public class AdaptOperatorLocalSearch<T,P> extends VariationReplaceLocalSearch<T>{
    protected AdaptSearchOperatorParameters<P> adapt;
    
    
    public AdaptOperatorLocalSearch( Variation_1_1<T> variation,
    								 AdaptSearchOperatorParameters<P> adapt, 
    								 GoalBasedReplacement<T,Double> replace ){
        super( variation, replace );
        this.adapt = adapt;
    }
    
    @Override
    public Tagged<T> apply(Tagged<T> x, Space<T> space){
		Goal<T,Double> goal = goal();
		Tagged<T> y = variation.apply(space, x);
        if( adapt != null )	adapt.apply(variation, goal.apply(x), goal.apply(y));
        Tagged<T> z = replace.apply(x, y);
        trace(x, z);
        return z;
    }    
}