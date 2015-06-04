package unalcol.evolution.haea;

/**
 * <p>Title: CAHaeaDescriptors</p>
 * <p>Description: Descriptors for the CAHAEA Algorithm.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CAHaeaDescriptor extends HaeaStrategyDescriptor{
    /**
     * Returns the Class that owns the PlugIn
     * @return Class The PlugIns owner class
     */
    @Override
    public Object owner() {
        return CAHaeaStrategy.class;
    }

    /**
     * Creates a CAHAEA statistics using the information from the population
     * and the operators rate information
     * @param tr CAHAEA Strategy object to be described
     */
    public double[] descriptors(HaeaStrategy tr) {
        CAHaeaStrategy st = (CAHaeaStrategy)tr;
        double[] d = super.descriptors(st);
        int s;
        if( st.ca != null){
            s = st.ca.aliveCells();
        }else{
            s = 200;
        }
        double[] nd = new double[d.length+1];
        nd[0] = s;
        for( int i=0; i<d.length; i++){
            nd[i+1] = d[i];
        }
        return nd;
    }

}
