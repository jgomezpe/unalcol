package unalcol.search.selection;

import unalcol.random.integer.IntRoulette;
import unalcol.search.Goal;
import unalcol.sort.Order;
import unalcol.sort.ReversedOrder;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.keymap.ValueOrder;
import unalcol.types.collection.vector.SortedVector;

public class Ranking<T,R> extends GoalBasedSelection<T,R> {
	public Ranking( Goal<T,R> goal ){ super(goal); }
	
	@Override
	public int[] apply(int n, R[] x) {
		Order order = goal().order();
		int s = x.length;
		SortedVector<KeyValue<Integer,R>> indexq = new SortedVector<KeyValue<Integer,R>>( 
				new ReversedOrder( new ValueOrder<Integer,R>(order) ) );
		for( int i=0; i<s; i++ ) indexq.add(new KeyValue<Integer,R>(i, x[i] ) );
		IntRoulette roulette = new IntRoulette(n);
		int[] sel = roulette.generate(n);
		for( int i=0; i<sel.length; i++ ) sel[i] = indexq.get(i).key();
		return sel;
	}

	@Override
	public int choose_one(R[] x) {
		return apply(1,x)[0];
	}
}