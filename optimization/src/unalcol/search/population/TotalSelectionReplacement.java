package unalcol.search.population;

import unalcol.search.selection.Elitism;
import unalcol.search.selection.Selection;
import unalcol.types.collection.vector.Vector;

public class TotalSelectionReplacement<T> implements PopulationReplacement<T> {
	protected Selection<T> selection;
	
	public TotalSelectionReplacement() {
		this( new Elitism<T>(1.0, 0.0) );
	}
	
	public TotalSelectionReplacement( Selection<T> selection ){
		this.selection = selection;
	}
	
	@Override
	public PopulationSolution<T> apply(PopulationSolution<T> current,
			PopulationSolution<T> next) {
		int n = current.value().size();
		double[] quality = new double[n+next.quality().length];
		System.arraycopy(current.quality(), 0, quality, 0, current.quality().length);
		System.arraycopy(next.quality(), 0, quality, n, next.quality().length);
		Vector<Integer> index = selection.apply(n, quality);
		Vector<T> finalPop = new Vector<T>();
		double[] finalQ = new double[n];
		int k=0;
		for( int i : index ){
			if( i < n ){
				finalPop.add(current.value().get(i));
				finalQ[k] = current.quality[i];
			}else{
				finalPop.add(next.value().get(i-n));
				finalQ[k] = next.quality[i-n];
			}
			k++;
		}
		return new PopulationSolution<T>(finalPop, finalQ);
	}	
}
