package optimization;

import unalcol.descriptors.WriteDescriptors;
import unalcol.optimization.OptimizationFunction;
import unalcol.search.solution.SolutionDescriptors;
import unalcol.services.Service;
import unalcol.types.object.Tagged;

public class Binary2RealTest extends EncodeTest<double[]>{
	public Binary2RealTest(OptimizationFunction<double[]> function, int EVALS){ 
		super( function, EVALS );
        Service.register(new SolutionDescriptors<double[]>(function), Tagged.class);
        Service.register(new WriteDescriptors(), Tagged.class);
	}
}