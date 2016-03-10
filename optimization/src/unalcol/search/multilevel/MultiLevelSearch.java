package unalcol.search.multilevel;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;

public class MultiLevelSearch<G,P,R> implements Search<P,R> {
	protected Search<G,R> lowLevelSearch;
	protected CodeDecodeMap<G, P> map;
	
	public MultiLevelSearch( Search<G,R> lowLevelSearch, CodeDecodeMap<G, P> map ) {
		this.lowLevelSearch = lowLevelSearch;
		this.map = map;
	}
	
	@Override
	public Solution<P> solve(Space<P> space, Goal<P,R> goal) {
		MultiLevelGoal<G, P, R> lowLevelGoal = new MultiLevelGoal<G,P, R>(goal, map);
		MultiLevelSpace<G, P> lowLevelSpace = new MultiLevelSpace<G, P>(space, map);
		Solution<G> sol = lowLevelSearch.solve(lowLevelSpace, lowLevelGoal);
		Solution<P> h_sol = new Solution<P>(map.decode(sol.object()));
		h_sol.set(Goal.class.getName(), goal);
		h_sol.set(Goal.GOAL_TEST, sol.info(Goal.class.getName()));
		return h_sol;
	}
}