package unalcol.evolution.haea;
import unalcol.collection.Vector;
import unalcol.exception.ParamsException;
import unalcol.integer.Roulette;
import unalcol.random.UsesRawGenerator;
import unalcol.real.Array;
import unalcol.search.variation.Variation;

/**
 * <p>Title: HaeaOperators</p>
 *
 * <p>Description: Abstract Manager of a collection of Operators for the HAEA Algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class HaeaOperators<T> extends UsesRawGenerator{
    /**
     * Roulette selection mechanism (for selecting the genetic operator)
     */
    protected Roulette roulette = new Roulette( new double[]{} );

    /**
     * Rates associated to each genetic operator per individual
     */
    protected Vector<double[]> rates = new Vector<double[]>();
    
    /**
     * Selected operator
     */
    protected Vector<Integer> sel_oper = new Vector<Integer>();

    /**
     * Selected operator
     */
    protected Vector<Integer> size_offspring_sel_oper = new Vector<Integer>();

    /**
     * Number of genetic operators per individual
     * @return Number of genetic operators per individual
     */
    public abstract int numberOfOperatorsPerIndividual();

    /**
     * Number of total genetic operators
     * @return Number of total genetic operators
     */
    public abstract int numberOfOperators();

    /**
     * Select an operator to be applied according to the rates encoded in the individual
     * @param x Rates to be taken into account
     * @return Index of the choosen operator
     */
    protected int select( double[] x ){
        roulette.setDensity(x);
        return roulette.next();
    }
    
    public void setSizeOffspring( int indIndex, int n ){
        size_offspring_sel_oper.set(indIndex, (Integer)n);
    }

    public int getSizeOffspring( int indIndex )  throws ParamsException{
        return size_offspring_sel_oper.get(indIndex);
    }

    /**
     * Select an operator to be applied according to the rates encoded in the individual
     * @param indIndex Individual to be taken into account
     * @return Index of the choosen operator
     */
    public int select( int indIndex )  throws ParamsException{
        sel_oper.set(indIndex, (Integer)select( rates.get(indIndex) ));
        return sel_oper.get(indIndex);
    }

    /**
     * Select an operator to be applied according to the rates encoded in the individual
     * @param indIndex Individual to be taken into account
     * @return Index of the choosen operator
     */
    public void unselect( int indIndex ){ sel_oper.set(indIndex, (Integer)(-1)); }

    /**
     * Gets the genetic operator associated to the given index and individual
     * @param indIndex Individual Index
     * @param operIndex Operator index
     * @return Genetic operator associated to the given index and individual
     */
    public abstract Variation<T> get( int indIndex, int operIndex );

    /**
     * Genetic operator reward mechanism
     * @param r Rates associated to an individual
     * @param operIndex Operator to be rewarded
     */
    public void reward(double[] r, int operIndex) {
        if( operIndex >= 0 ){
            r[operIndex] += (1.0 - r[operIndex]) * raw().next();
            Array.normalize(r);
        }
    }

    /**
     * Genetic operator punish mechanism
     * @param r Rates associated to an individual
     * @param operIndex Operator to be punished
     */
    public void punish(double[] r, int operIndex) {
        if( operIndex >= 0 ){
            r[operIndex] -= r[operIndex] * raw().next();
            Array.normalize(r);
        }    
    }
    
    public void reward( int indIndex ) throws ParamsException{
        reward(rates.get(indIndex), sel_oper.get(indIndex));
        // @TODO: Adaptation of operators in Haea
        //get(indIndex, sel_oper.get(indIndex)).adapt(1.0);
    }

    public void punish( int indIndex ) throws ParamsException {
        punish(rates.get(indIndex), sel_oper.get(indIndex));
        // @TODO: Adaptation of operators in Haea
        //get(indIndex, sel_oper.get(indIndex)).adapt(-1.0);
    }

    
    /**
     * Updates the rates associated to the genetic operators
     * @param rates New genetic operators rates
     */
    public void resize( int n ){
        int m = rates.size();
        if( m < n ){
            for( int i=m; i<n; i++){
                double[] r = raw().raw( numberOfOperatorsPerIndividual() );
                Array.normalize(r);
                rates.add(r);            
                sel_oper.add(-1);
                size_offspring_sel_oper.add(1);
            }
        }else{
            while( n<m ){
                m--;
                rates.remove(m);
                sel_oper.remove(m);
                size_offspring_sel_oper.remove(m);
            }
        }
    }

    /**
     * Genetic operator rates associated to a given individual
     * @param indIndex Individual
     * @return Genetic operator rates associated to a given individual
     */
    public double[] rates( int indIndex ) throws ParamsException{ return rates.get(indIndex); }

    /**
     * Genetic operator rates
     * @return Genetic operator rates
     */
    public Vector<double[]> rates(){ return rates; }
}