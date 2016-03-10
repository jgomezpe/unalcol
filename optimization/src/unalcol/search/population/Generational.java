package unalcol.search.population;

public class Generational<T> implements PopulationReplacement<T>{

	@Override
	public Population<T> apply(Population<T> current,
			Population<T> next) {
		return next;
	}

}
