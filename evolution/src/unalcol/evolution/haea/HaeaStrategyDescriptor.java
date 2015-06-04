package unalcol.evolution.haea;
import unalcol.descriptors.*;
import unalcol.types.collection.vector.*;


/**
 * <p>Title: HaeaStrategyDescriptors</p>
 * <p>Description: Descriptors for the HAEA offspring generation strategy.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class HaeaStrategyDescriptor  implements DescriptorsService {
    /**
     * Returns the Class that owns the PlugIn
     * @return Class The PlugIns owner class
     */
    @Override
    public Object owner() {
        return HaeaStrategy.class;
    }

    public static boolean printOperatorStat = true;

    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param obj HAEAStrategy object to be described
     */
    public double[] descriptors(Object obj) {
        return descriptors((HaeaStrategy)obj);
    }

    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param tr HAEAStrategy object to be described
     */
    public double[] descriptors(HaeaStrategy tr) {
        Vector rates = tr.operators.rates();
        if (rates.size() > 0) {
            int opers_number = ((double[]) rates.get(0)).length;
            double[] avg_opers = new double[opers_number];
            for (int j = 0; j < opers_number; j++) {
                avg_opers[j] = 0.0;
                for (int i = 0; i < rates.size(); i++) {
                    avg_opers[j] += ((double[]) rates.get(i))[j];
                }
                avg_opers[j] /= rates.size();
            }
            return avg_opers;
        } else {
            int opers_number = tr.operators.numberOfOperators();
            double[] avg_opers = new double[opers_number];
            for (int j = 0; j < opers_number; j++) {
                avg_opers[j] = 1.0 / opers_number;
            }
            return avg_opers;
        }
    }
}