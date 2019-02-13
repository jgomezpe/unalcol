package unalcol.search.multilevel;

import unalcol.search.Goal;
import unalcol.sort.Order;

public class MultiLevelGoal<G,P,R> extends Goal<G,R>{
	protected Goal<P,R> goal;
	protected CodeDecodeMap<G, P> map; 
	
	public MultiLevelGoal( Goal<P,R> goal, CodeDecodeMap<G, P> map ){
		this.goal = goal;
		this.map = map;
	}

	@Override
	public R compute(G x) {
		return goal.apply(map.decode(x));
	}

	@Override
	public R apply(G x) { return compute(x); }

	@Override
	public boolean deterministic(){ return goal.deterministic(); }
	
	@Override
	public Order order() {
		return goal.order();
	}	
}
