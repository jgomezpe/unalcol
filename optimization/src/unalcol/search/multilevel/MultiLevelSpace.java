package unalcol.search.multilevel;

import unalcol.search.space.Space;

public class MultiLevelSpace<G,P> extends Space<G> {
	protected Space<P> space;
	protected CodeDecodeMap<G, P> map;

	public MultiLevelSpace( Space<P> space, CodeDecodeMap<G, P> map ) {
		this.space = space;
		this.map = map;
	}
	
	@Override
	public G pick() {
		return map.code(space.pick());
	}

	@Override
	public boolean feasible(G x) {
		return space.feasible(map.decode(x));
	}

	@Override
	public double feasibility(G x) {
		return space.feasibility(map.decode(x));
	}

	@Override
	public G repair(G x) {
		return map.code(space.repair(map.decode(x)));
	}


}
