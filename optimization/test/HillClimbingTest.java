
import unalcol.optimization.hillclimbing.HillClimbing;
import unalcol.optimization.real.HyperCubeRandomSampler;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.space.SpaceSampler;




public class HillClimbingTest{
    
    public static void main(String[] args){
    	SpaceSampler<double[]> sampler = new HyperCubeRandomSampler();
        HillClimbing<double[]> hc = new HillClimbing<double[]>( sampler, 
        		new IntensityMutation(0.1, new SimplestSymmetricPowerLawGenerator()), true, 1000);
        
    }
}