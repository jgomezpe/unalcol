package unalcol.evolution.haea;

import unalcol.search.variation.Variation;

/**
 * <p>Title: SimpleHaeaOperators</p>
 *
 * <p>Description: The Basic and original Managment strategy of operators for the HAEA Algorithm</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SimpleHaeaOperators<T> extends HaeaOperators<T>{
    /**
     * Genetic operator to be used by each individual
     */
    protected Variation<T>[] opers;

    /**
     * Creates a simple hAEA Operators manager
     * @param opers Genetic operator to be used by HAEA
     */
    @SafeVarargs
	public SimpleHaeaOperators( Variation<T>... opers ) {
        this.opers = opers;
    }


    /**
     * Number of genetic operators per individual
     * @return Number of genetic operators per individual
     */
    public int numberOfOperatorsPerIndividual(){
      return opers.length;
    }

    /**
     * Number of total genetic operators
     * @return Number of total genetic operators
     */
    public int numberOfOperators(){
      return opers.length;
    }


    /**
     * Gets the genetic operator associated to the given index
     * @param indIndex Individual Not used since operators are the same for each individual
     * @param operIndex Operator index
     * @return Genetic operator associated to the given index
     */
    public Variation<T> get( int indIndex, int operIndex ){
        return opers[operIndex];
    }
}
