package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.selection.Elitism;
import unalcol.search.selection.Selection;
import unalcol.types.collection.vector.Vector;

public class PopulationSolution<T> {
	protected Vector<T> value;
	protected double[] quality;
	protected Selection<T> selection;
	
	public PopulationSolution( Vector<T> value, double[] quality, Selection<T> selection ){
		this.value = value;
		this.quality = quality;
		this.selection = selection;
	}
	
	public PopulationSolution( Vector<T> value, double[] quality ){
		this( value, quality, new Elitism<T>(1.0, 0.0));
	}
	
	public PopulationSolution( Vector<T> value, Goal<T> goal, Selection<T> selection ){
		this( value, goal.quality(value), selection );
	}
	
	public PopulationSolution( Vector<T> value, Goal<T> goal ){
		this( value, goal.quality(value) );
	}
	
	public Vector<T> value(){ return value; }
	
	public double[] quality(){ return quality; }

	public double[] quality( Goal<T> goal ){
		if( goal.nonStationary() ){ quality = goal.quality(value); }
		return quality; 
	}
	    
    public Solution<T> pick(){
    	int k = selection.choose_one(quality);
    	return new Solution<T>(value.get(k), quality[k]);
    }	
}