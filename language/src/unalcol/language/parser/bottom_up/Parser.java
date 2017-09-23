package unalcol.language.parser.bottom_up;

import unalcol.types.collection.vector.Vector;

public class Parser<T> {
	protected Vector<Rule<T>> rule;

	public Parser( Rule<T>[] rules ){
		rule = new Vector<Rule<T>>();
		for( Rule<T> r:rules ) rule.add(r);
	}
	
	public Vector<T> next(Vector<T> elements){
		boolean flag = true;
		while( flag ){
			Vector<Token<T>> options = new Vector<Token<T>>();
			for( Rule<T> r : rule ){
				options.add(r.next(elements));
			}
			int best = 0;
			for( int i=1; i<rule.size(); i++ ) if( options.get(i).consumed() > options.get(best).consumed() ) best = i;
	
			Token<T> t = options.get(best);
			int c = t.consumed();
			flag = (c>0);
			if( flag ){
				int start = t.start();
				elements.set(start, t.element());
				c--;
				start++;
				for( int i=0; i<c; i++ ) elements.remove(start);
			}	
		}
		return elements;
	}
}
