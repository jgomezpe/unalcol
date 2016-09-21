package unalcol.evolution.haea;

import unalcol.search.selection.Selection;
import unalcol.search.solution.Solution;
import unalcol.search.variation.Variation;
import unalcol.types.collection.vector.Vector;

public class HaeaVariation<T> extends Variation<T>{
    /**
     * Set of genetic operators that are used by CEA for evolving the solution chromosomes
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
    public Vector<Integer> select( int id, Solution<T>[] population ){
        return null;
    }

    /**
     * Determines if the individual can be selected as first parent
     * @param id Individuals's id
     * @return <i>true</i> if the individual can be selected as first parent, <i>false</i> otherwise
     */
    public boolean available( int id ){
        return true;
    }
    
    @SuppressWarnings("unchecked")
	protected Solution<T>[] array_sol( int n ){
    	return (Solution<T>[])new Solution[n];
    }

    public HaeaOperators<T> operators(){ return operators; }
    
	@Override
	public Solution<T>[] apply(@SuppressWarnings("unchecked") Solution<T>... population) {
		int n = population.length;
    	operators.resize(n);
		Vector<Solution<T>> buffer = new Vector<Solution<T>>();
        for (int i = 0; i<n; i++) {
            // next line added  Feb 25, 2011
            if( available(i) ){
                int oper = operators.select(i);
                Variation<T> o = operators.get(i, oper);
                Vector<Integer> subset = select(i, population);
                Solution<T>[] pop;
                if(subset==null){
                	pop = population;
                }else{
                	pop = array_sol(subset.size());
                	int id=0;
                	for( int k : subset ){
                		pop[id] = population[k];
                		id++;
                	}
                }
                pop = selection.pick(o.arity()-1, pop);
                Solution<T>[] parent = array_sol(o.arity());
                parent[0] = population[i];
                for( int k=0; k<pop.length; k++ ){
                    parent[k+1] = pop[k];
                }
                //@TODO Check how to use the space???
                Solution<T>[] offspring = o.apply(parent);
                operators.setSizeOffspring(i, offspring.length);
                for( Solution<T> x : offspring ){                	
                    buffer.add(x);
                }
            }else{
                operators.unselect(i);
                operators.setSizeOffspring(i, 1);
                buffer.add(population[i]);
            }
        }
        return buffer.toArray();
	}   
}