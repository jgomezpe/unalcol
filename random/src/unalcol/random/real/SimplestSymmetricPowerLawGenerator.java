/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

/**
 *
 * @author jgomez
 */
public class SimplestSymmetricPowerLawGenerator  extends SymmetricGenerator{
    public SimplestSymmetricPowerLawGenerator(){
        super(new SimplestGeneralizedPowerLawGenerator());
    }
}
