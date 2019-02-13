/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.method.annealing;

/**
 *
 * @author jgomez
 */
public class SimpleSimulatedAnnealingScheme implements SimulatedAnnealingScheme{
    protected double K;

    public SimpleSimulatedAnnealingScheme( double K ){
        this.K = K;
    }
    
    @Override
    public double get(double t) {
        return t/K;
    }
    
}
