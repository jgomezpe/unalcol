package unalcol.evolution.haea;
import unalcol.descriptors.*;
import unalcol.services.MicroService;
import unalcol.types.real.Statistics;
import unalcol.types.real.matrix.DoubleMatrixUtil;


/**
 * <p>Title: HaeaStrategyDescriptors</p>
 * <p>Description: Descriptors for the HAEA offspring generation strategy.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class SimpleHaeaOperatorsDescriptor<T> extends MicroService<HaeaOperators<T>> implements Descriptors<HaeaOperators<T>> {
    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param tr HAEAStrategy object to be described
     */
    public double[] descriptors( ) {
    	HaeaOperators<T> operators = caller();
    	Object[] obj = operators.rates().toArray();
		double[][] rates = (double[][])obj;
		Statistics[] stat = DoubleMatrixUtil.statistics(rates, false);
		double[] avg = new double[stat.length];
		for( int i=0; i<avg.length; i++ ){
			avg[i] = stat[i].avg;
		}
		return avg;
    }
}