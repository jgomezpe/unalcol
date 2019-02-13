package unalcol.evolution.haea;


/**
 * <p>Title: CAHaeaDescriptors</p>
 * <p>Description: Descriptors for the CAHAEA Algorithm.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CAHaeaStepDescriptor<T> extends HaeaStepDescriptors<T>{
    /**
     * Creates a CAHAEA statistics using the information from the population
     * and the operators rate information
     * @param tr CAHAEA Strategy object to be described
     */
	@Override
    public double[] descriptors( HaeaStep<T> step ) {
        double[] d = super.descriptors(step);
        if( step instanceof CellularHaeaStep ){
        	CellularHaeaStep<T> ca_step = (CellularHaeaStep<T>)step;
            int s;
            if( ca_step.ca != null){
                s = ca_step.ca.aliveCells();
            }else{
                s = 200;
            }
            double[] nd = new double[d.length+1];
            nd[0] = s;
            System.arraycopy(d, 0, nd, 1, d.length);
            d = nd;
        }
        return d;
    }

}
