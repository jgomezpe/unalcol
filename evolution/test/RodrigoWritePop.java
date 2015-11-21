import java.io.Writer;

import unalcol.io.Write;
import unalcol.search.population.PopulationSolution;

public class RodrigoWritePop<T> extends Write<PopulationSolution<T>> {

	@Override
	public void write(PopulationSolution<T> pop, Writer write) throws Exception {
		// TODO Auto-generated method stub
		T t = pop.value(3);
		write.write("Rodrigo dice ");
		Write.apply(t, write);
	}

}
