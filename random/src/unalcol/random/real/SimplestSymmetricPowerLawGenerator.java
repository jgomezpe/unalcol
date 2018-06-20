/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class SimplestSymmetricPowerLawGenerator  extends SymmetricGenerator{
    public SimplestSymmetricPowerLawGenerator(){
        super();
        one_side = new SimplestGeneralizedPowerLawGenerator();
    }
}