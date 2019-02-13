package unalcol.search.selection;

import unalcol.integer.Roulette;
import unalcol.search.Goal;
import unalcol.sort.Order;
import unalcol.sort.Reversed;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.keymap.ValueOrder;
import unalcol.collection.vector.Sorted;

public class Ranking<T,R> extends GoalBasedSelection<T,R> {
	public Ranking( Goal<T,R> goal ){ super(goal); }
	
	@Override
	public int[] apply(int n, R[] x) {
		Order order = goal().order();
		int s = x.length;
		Sorted<KeyValue<Integer,R>> indexq = new Sorted<KeyValue<Integer,R>>( 
				new Reversed( new ValueOrder<Integer,R>(order) ) );
		for( int i=0; i<s; i++ ) indexq.add(new KeyValue<Integer,R>(i, x[i] ) );
		Roulette roulette = new Roulette(n);
		int[] sel = roulette.generate(n);
		try{ for( int i=0; i<sel.length; i++ ) sel[i] = indexq.get(i).key(); }catch(Exception e){}
		return sel;
	}

	@Override
	public int choose_one(R[] x) {
		return apply(1,x)[0];
	}
}