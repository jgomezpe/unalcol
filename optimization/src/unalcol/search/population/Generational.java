package unalcol.search.population;

public class Generational<T> implements PopulationReplacement<T>{

	@Override
	public PopulationSolution<T> apply(PopulationSolution<T> current,
			PopulationSolution<T> next) {
		return next;
	}

}
