package unalcol.search.population;

import unalcol.Tagged;

public class Generational<T> implements PopulationReplacement<T>{
	@Override
	public Tagged<T>[] apply(Tagged<T>[] current, Tagged<T>[] next) {
		return next;
	}
}