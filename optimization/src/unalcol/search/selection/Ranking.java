package unalcol.search.selection;

import unalcol.random.integer.IntRoulette;
import unalcol.sort.Order;
import unalcol.sort.ReversedOrder;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.keymap.ValueOrder;
import unalcol.types.collection.vector.SortedVector;

public class Ranking<R> implements QualityBasedSelection<R> {
	@Override
	public int[] apply(int n, R[] x, Order<R> order) {
		int s = x.length;
		SortedVector<KeyValue<Integer,R>> indexq = new SortedVector<KeyValue<Integer,R>>( 
				new ReversedOrder<KeyValue<Integer,R>>( new ValueOrder<Integer,R>(order) ) );
		for( int i=0; i<s; i++ ) indexq.add(new KeyValue<Integer,R>(i, x[i] ) );
		IntRoulette roulette = new IntRoulette(n);
		int[] sel = roulette.generate(n);
		for( int i=0; i<sel.length; i++ ) sel[i] = indexq.get(i).key();
		return sel;
	}

	@Override
	public int choose_one(R[] x, Order<R> order) {
		return apply(1,x,order)[0];
	}
}