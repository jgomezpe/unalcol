package unalcol.search.multilevel;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.Tagged;
import unalcol.Thing;
import unalcol.search.space.Space;

public class MultiLevelSearch<G,P,R> extends Thing implements Search<P,R> {
	protected Search<G,R> lowLevelSearch;
	protected CodeDecodeMap<G, P> map;
	
	public MultiLevelSearch( Search<G,R> lowLevelSearch, CodeDecodeMap<G, P> map ) {
		this.lowLevelSearch = lowLevelSearch;
		this.map = map;
	}
	
	@Override
	public Tagged<P> solve(Space<P> space, Goal<P,R> goal) {
		MultiLevelGoal<G, P, R> lowLevelGoal = new MultiLevelGoal<G,P, R>(goal, map);
		MultiLevelSpace<G, P> lowLevelSpace = new MultiLevelSpace<G, P>(space, map);
		Tagged<G> sol = lowLevelSearch.solve(lowLevelSpace, lowLevelGoal);
		Tagged<P> h_sol = new Tagged<P>(map.decode(sol.unwrap()));
		return h_sol;
	}
}