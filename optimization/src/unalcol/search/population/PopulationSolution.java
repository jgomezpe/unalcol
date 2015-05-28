package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.types.collection.vector.Vector;

public class PopulationSolution<T> {
	protected Vector<T> value;
	protected double[] quality;
	
	public PopulationSolution( Vector<T> value, double[] quality ){
		this.value = value;
		this.quality = quality;
	}
	
	public PopulationSolution( Vector<T> value, Goal<T> goal ){
		this.value = value;
		this.quality = goal.quality(value);
	}
	
	public Vector<T> value(){ return value; }
	
	public double[] quality(){ return quality; }

	public double[] quality( Goal<T> goal ){
		quality = goal.quality(value);
		return quality; 
	}
	
    public int pick_index(){
        int k=0;
        for( int i=1; i<quality.length; i++ ){
            if( quality[k] < quality[i] ){ k=i; }            
        }
        return k;
    }
    
    public Solution<T> pick(){
    	int k = pick_index();
    	return new Solution<T>(value.get(k), quality[k]);
    }	
}