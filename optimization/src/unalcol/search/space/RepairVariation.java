/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

/**
 *
 * @author jgomez
 */
public abstract class RepairVariation<T> extends Variation<T>{
    protected Variation<T> operator;
    protected Repair<T> repair;

    public RepairVariation( Variation<T> operator, Repair<T> repair ){
        this.operator = operator;
        this.repair = repair;
    }
        
    @Override
    public T apply(Space<T> space, T x) {
        return repair.apply(space, operator.apply(x));
    }    
}
