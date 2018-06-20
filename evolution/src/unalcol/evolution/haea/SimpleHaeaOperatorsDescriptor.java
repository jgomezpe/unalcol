package unalcol.evolution.haea;
import unalcol.descriptors.*;
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
public class SimpleHaeaOperatorsDescriptor<T> implements Descriptors{
    /**
     * Creates a HAEA statistics using the information from the population
     * and the operators rate information
     * @param tr HAEAStrategy object to be described
     */
    public double[] descriptors( HaeaOperators<T> operators ) {
    	double[][] rates = new double[operators.rates.size()][];
    	for( int i=0; i<rates.length; i++ ) rates[i] = operators.rates(i);
		Statistics[] stat = DoubleMatrixUtil.statistics(rates, false);
		double[] avg = new double[stat.length];
		for( int i=0; i<avg.length; i++ ) avg[i] = stat[i].avg; 
		return avg;
    }

	@SuppressWarnings("unchecked")
	@Override
	public double[] descriptors(Object obj){ return descriptors((HaeaOperators<T>)obj);	}
}