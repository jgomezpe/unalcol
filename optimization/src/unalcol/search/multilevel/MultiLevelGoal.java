package unalcol.search.multilevel;

import unalcol.search.Goal;

public class MultiLevelGoal<G,P> extends Goal<G>{
	protected Goal<P> goal;
	protected CodeDecodeMap<G, P> map; 
	
	public MultiLevelGoal( Goal<P> goal, CodeDecodeMap<G, P> map ){
		this.goal = goal;
		this.map = map;
	}

	@Override
	public boolean test(G x) {
		return goal.test(map.decode(x));
	}

	@Override
	public double quality(G x) {
		return goal.quality(map.decode(x));
	}

	@Override
	public boolean nonStationary() {
		return goal.nonStationary();
	}

	@Override
	public boolean qTest(double q) {
		return goal.qTest(q);
	}
	
	
}
