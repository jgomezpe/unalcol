package unalcol.evolution.genotype.real.util;
import unalcol.evolution.GrowingFunction;
import unalcol.types.real.array.*;


/**
 * <p>Title:  ScaleRealVectorGrowingFunction</p>
 * <p>Description: Scales a [0,1.0) ereal vector encoding to the desired scale</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class ScaleRealVectorGrowingFunction<G> extends GrowingFunction<G, double[]>{
    protected RealVectorLinealScale scale = null;
    protected GrowingFunction<G,double[]> lowLevel;

    public ScaleRealVectorGrowingFunction( double[] min, double[] max ){
        scale = new RealVectorLinealScale(min, max);
        lowLevel = new GrowingFunction();
    }

    public ScaleRealVectorGrowingFunction( GrowingFunction<G,double[]> lowLevel,
            double[] min, double[] max ){
        this.lowLevel = lowLevel;
        scale = new RealVectorLinealScale(min, max);
    }
    
    public double[] get( G genome ){
        return scale.apply(lowLevel.get(genome));
    }
    
    public G set( double[] thing ){
        return lowLevel.set(scale.inverse(thing));
    }
}