package unalcol.search.multilevel;

import unalcol.search.BasicGoalBased;
import unalcol.search.Search;
import unalcol.search.space.Space;
import unalcol.types.object.Tagged;
import unalcol.types.object.TaggedManager;

public class MultiLevelSearch<G,P,R> extends BasicGoalBased<P, R> implements Search<P,R> {
	protected Search<G,R> lowLevelSearch;
	protected CodeDecodeMap<G, P> map;
	protected TaggedManager<P> manager;
	
	public MultiLevelSearch( Search<G,R> lowLevelSearch, CodeDecodeMap<G, P> map ) {
		this.lowLevelSearch = lowLevelSearch;
		this.map = map;
		this.manager = new TaggedManager<P>() {};
	}
	
	@Override
	public Tagged<P> solve(Space<P> space) {
		MultiLevelGoal<G, P, R> lowLevelGoal = new MultiLevelGoal<G,P, R>(goal(), map);
		MultiLevelSpace<G, P> lowLevelSpace = new MultiLevelSpace<G, P>(space, map);
		lowLevelSearch.setGoal(lowLevelGoal);
		Tagged<G> sol = lowLevelSearch.solve(lowLevelSpace);
		Tagged<P> h_sol = manager.wrap(map.decode(sol.unwrap()));
		return h_sol;
	}
}