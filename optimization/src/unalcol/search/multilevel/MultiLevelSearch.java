package unalcol.search.multilevel;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.Solution;
import unalcol.search.space.Space;

public class MultiLevelSearch<G,P> implements Search<P> {
	protected Search<G> lowLevelSearch;
	protected CodeDecodeMap<G, P> map;
	
	public MultiLevelSearch( Search<G> lowLevelSearch, CodeDecodeMap<G, P> map ) {
		this.lowLevelSearch = lowLevelSearch;
		this.map = map;
	}
	
	@Override
	public Solution<P> apply(Space<P> space, Goal<P> goal) {
		MultiLevelGoal<G, P> lowLevelGoal = new MultiLevelGoal<G,P>(goal, map);
		MultiLevelSpace<G, P> lowLevelSpace = new MultiLevelSpace<G, P>(space, map);
		Solution<G> sol = lowLevelSearch.apply(lowLevelSpace, lowLevelGoal);
		return new Solution<P>(map.decode(sol.value()), sol.quality());
	}

	@Override
	public void init() {
		lowLevelSearch.init();
	}

}
