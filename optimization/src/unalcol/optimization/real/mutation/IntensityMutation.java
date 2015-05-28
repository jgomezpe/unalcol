/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.optimization.real.mutation;
import unalcol.random.real.DoubleGenerator;

/**
 *
 * @author jgomezpe
 */
public class IntensityMutation extends Mutation{
    // Mutation definitions
    protected double sigma;
    protected DoubleGenerator g;
    protected AdaptMutationIntensity adapt;
    protected double[] delta;
    
    public IntensityMutation( double sigma, DoubleGenerator g, 
                              PickComponents components, 
                              AdaptMutationIntensity adapt){
        super(components);
        this.sigma = sigma;
        this.g = g;
        this.adapt = adapt;
    }
    
    public IntensityMutation(double sigma, DoubleGenerator g ){
        this( sigma, g, null, null);
    }
    
    protected double[] delta(int DIMENSION){
        if( components != null ){
            if( delta.length == DIMENSION ){
                for( int i=0; i<indices.length; i++){
                    delta[indices[i]]=0.0;
                }
            }else{
                delta = new double[DIMENSION];
            }
            indices = components.get(DIMENSION);
            for( int i=0; i<indices.length; i++ ){
                   delta[indices[i]] =  sigma*g.generate();
            }
        }else{
            for( int i=0; i<delta.length; i++){
                   delta[i] =  sigma*g.generate();
            }
        }
        return delta;
    }
        
    @Override
    public double[] apply( double[] x ){
        double[] y = x.clone();
        double[] z = delta(x.length);
        for( int i=0; i<y.length; i++){
            y[i] += z[i];
        }
        return y;
    }

    @Override
    public void adapt( double productivity ){
        if( adapt != null ){
            sigma = adapt.apply(sigma, productivity);
        }
    }
    
  /**
   * Class of objects the operator is able to process
   * @return Class of objects the operator is able to process
   */
  public Object owner(){
      return double[].class;
  }
    
}