package unalcol.evolution.haea;

import unalcol.search.selection.Selection;
import unalcol.search.variation.Variation;
import unalcol.collection.Vector;
import unalcol.object.Tagged;

public class HaeaVariation<T> implements Variation<T>{
	/**
	 * Set of genetic operators that are used by CEA for evolving the Tagged chromosomes
	 */
	protected HaeaOperators<T> operators = null;

	/**
	 * Extra parent selection mechanism
	 */
	protected Selection<T> selection;

	public HaeaVariation( Selection<T> parent_selection, HaeaOperators<T> operators ){
    		this.selection = parent_selection;
    		this.operators = operators;
	}
    
    	/**
    	 * Gets a subpopulation that can be used for selecting a second parent
    	 * @param id First parent
    	 * @param population Full Population
    	 * @return A subpopulation that can be used for selecting a second parent
    	 */
	public Vector<Integer> select( int id, Tagged<T>[] population ){
		return null;
	}

    	/**
    	 * Determines if the individual can be selected as first parent
    	 * @param id Individuals's id
    	 * @return <i>true</i> if the individual can be selected as first parent, <i>false</i> otherwise
    	 */
	public boolean available( int id ){ return true; }
    
	@SuppressWarnings("unchecked")
	protected Tagged<T>[] array_sol( int n ){	return (Tagged<T>[])new Tagged[n]; }

    	public HaeaOperators<T> operators(){ return operators; }
    
    	@SuppressWarnings("unchecked")
    	@Override
    	public Tagged<T>[] apply(Tagged<T>... population){
		int n = population.length;
		operators.resize(n);
		Vector<Tagged<T>> buffer = new Vector<Tagged<T>>();
		for (int i = 0; i<n; i++) {
			// next line added  Feb 25, 2011
			if( available(i) ){
				try{
					int oper = operators.select(i);
					Variation<T> o = operators.get(i, oper);
					Vector<Integer> subset = select(i, population);
					Tagged<T>[] pop;
					if(subset==null) pop = population;
					else{
						pop = array_sol(subset.size());
						int id=0;
						for( int k : subset ){
							pop[id] = population[k];
							id++;
						}
					}
					pop = selection.pick(o.arity()-1, pop);
					Tagged<T>[] parent = array_sol(o.arity());
					parent[0] = population[i];
					for( int k=0; k<pop.length; k++ ) parent[k+1] = pop[k]; 
					//@TODO Check how to use the space???
					Tagged<T>[] offspring = o.apply(parent);
					operators.setSizeOffspring(i, offspring.length);
					for( Tagged<T> x : offspring ) buffer.add(x);
				}catch(Exception e){}	
			}else{
				operators.unselect(i);
				operators.setSizeOffspring(i, 1);
				buffer.add(population[i]);
			}
		}
		Object[] obj = buffer.toArray();
		Tagged<T>[] b = (Tagged<T>[])new Tagged[obj.length];
		for( int i=0; i<b.length; i++) b[i] = (Tagged<T>)obj[i];
		return b;
	}   
}