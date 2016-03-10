package unalcol.evolution.es;

import unalcol.clone.Clone;
import unalcol.search.Goal;
import unalcol.search.population.Population;
import unalcol.search.population.PopulationReplacement;
import unalcol.search.selection.Elitism;
import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;


public abstract class ESReplacement<T> implements PopulationReplacement<T> {
	protected int mu;
	protected Selection<T> selection;
	
    public ESReplacement( int mu ) {
       this( mu, new Elitism<T>(1.0, 0.0) );
    }
		    
    public ESReplacement( int mu, Selection<T> selection ) {
    	this.mu = mu;
    	this.selection = selection;
    }
    
    public abstract Population<T> pool(Population<T> current, Population<T> next);

	@SuppressWarnings("unchecked")
	@Override
	public Population<T> apply(Population<T> current, Population<T> next) {
		String gName = Goal.class.getName();
		Object goal = current.data(gName);
		Population<T> p = pool( current, next );
		int[] np = selection.apply(mu, p.object());
		Solution<T>[] ns = (Solution<T>[])new Solution[mu];
		for( int i=0; i<mu; i++){
			ns[i] = (Solution<T>)Clone.create(p.get(np[i]));
			ns[i].set(gName, goal);
		}
		p = new Population<T>(ns);
		p.set(gName, goal);
		return p;
	}  
}