/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.random.real.SimplestSymmetricPowerLawGenerator;

/**
 *
 * @author jgomez
 */
public class PowerLawMutation extends IntensityMutation {
  /**
   * Creates a Gaussian Mutation with the given standard deviation per component
   * @param _sigma Standard deviation per component
   */
  public PowerLawMutation( double sigma ) {
      super(sigma, new SimplestSymmetricPowerLawGenerator());
  }

    public PowerLawMutation( double sigma, PickComponents components){
        super(sigma, new SimplestSymmetricPowerLawGenerator(), components);
    }
}
