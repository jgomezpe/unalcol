package unalcol.search.multilevel;

import unalcol.search.Goal;
import unalcol.sort.Order;

public class MultiLevelGoal<G,P,R> implements Goal<G,R>{
	protected Goal<P,R> goal;
	protected CodeDecodeMap<G, P> map; 
	
	public MultiLevelGoal( Goal<P,R> goal, CodeDecodeMap<G, P> map ){
		this.goal = goal;
		this.map = map;
	}

	@Override
	public R apply(G x) {
		return goal.apply(map.decode(x));
	}

	@Override
	public boolean nonStationary() {
		return goal.nonStationary();
	}
	
	@Override
	public Order<R> order() {
		return goal.order();
	}	
}
