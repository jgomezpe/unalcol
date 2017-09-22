package unalcol.types.real.array;
import unalcol.descriptors.*;
import unalcol.services.MicroService;

/**
 * <p>Descriptor Service for double arrays</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class DoubleArrayDescriptors extends MicroService<double[]>  implements Descriptors<double[]>{
 
    /**
     * Gets a numeric description of the given numeric values (this method produces just a clone)
     * @param ind Numeric values to be described using numeric values
     * @return Numeric description of the given numeric values (just creates a clone)
     */
    public double[] descriptors() {
        return caller().clone();
    }
}