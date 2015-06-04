package unalcol.evolution.haea;

import unalcol.descriptors.*;

/**
 * <p>Title: HaeaDescriptors</p>
 * <p>Description: Descriptors for the HAEA Algorithm.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class HaeaDescriptor implements DescriptorsService {
    /**
     * Returns the Class that owns the PlugIn
     * @return Class The PlugIns owner class
     */
    public Object owner() {
        return HAEA.class;
    }

    public static boolean printOperatorStat = true;

    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param obj HAEA object
     */
    public double[] descriptors(Object obj) {
        return descriptors((HAEA)obj);
    }

    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param tr HAEA object
     */
    public double[] descriptors(HAEA tr) {
        DescriptorsService d = DescriptorsProvider.get(tr.generation());
        return d.descriptors(tr.generation());
    }

}
