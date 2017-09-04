package unalcol.random.real;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * <p>Gaussian random number generator.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class GaussianGenerator extends StandardGaussianGenerator {
  /**
   * mean
   */
  double miu = 0.0;
  /**
   * standard deviation
   */
  double sigma = 1.0;

  /**
   * Constructor: Creates a Gaussian Number Generator G~(miu,sigma)
   * @param miu1 Mean
   * @param sigma1 standard deviation
   */
  public GaussianGenerator( double miu1, double sigma1 ){
    super();
    miu = miu1;
    sigma = sigma1;
  }

  /**
   * Returns a random double number
   * @return A random double number
   */
  @Override
  public Double next() {
    return (sigma*super.next() + miu);
  }

/*
  @Override
  public DoubleGenerator new_instance(){
	  RawGenerator g = (RawGenerator)Service.get().get(RawGenerator.name,this);
      DoubleGenerator dg = new GaussianGenerator(miu, sigma);
      RawGenerator.set(dg, g.new_instance());
      return dg;
  }    
*/  
}