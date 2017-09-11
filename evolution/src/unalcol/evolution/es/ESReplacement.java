package unalcol.evolution.es;

import unalcol.Tagged;
import unalcol.Thing;
import unalcol.clone.Clone;
import unalcol.search.GoalBased;
import unalcol.search.population.PopulationReplacement;
import unalcol.search.selection.Elitism;
import unalcol.search.selection.Selection;


public abstract class ESReplacement<T> extends Thing implements PopulationReplacement<T>, GoalBased<T, Double> {
	protected int mu;
	protected Selection<T> selection=null;
	
    public ESReplacement( int mu ){
       this.mu=mu;
    }
		    
    public ESReplacement( int mu, Selection<T> selection ) {
    	this.mu = mu;
    	this.selection = selection;
    }
    
    public abstract Tagged<T>[] pool(Tagged<T>[] current, Tagged<T>[] next);

	@SuppressWarnings("unchecked")
	@Override
	public Tagged<T>[] apply(Tagged<T>[] current, Tagged<T>[] next) {
		if( selection == null )	selection = new Elitism<T,Double>(goal(), 1.0, 0.0);
		Tagged<T>[] p = pool( current, next );
		int[] np = selection.apply(mu, p);
		Tagged<T>[] ns = (Tagged<T>[])new Tagged[mu];
		for( int i=0; i<mu; i++) ns[i] = (Tagged<T>)Clone.create(p[np[i]]);
		return ns;
	}  
}