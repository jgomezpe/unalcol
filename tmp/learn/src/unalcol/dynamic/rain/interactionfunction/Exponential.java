package unalcol.dynamic.rain.interactionfunction;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Exponential implements InteractionFunction {

  protected double pow = 2.0;

  public Exponential( double _pow ){
    pow = _pow;
  }

  @Override
  public double evaluate( double x ){
    x /= Math.sqrt(2.0);
    return Math.exp(-x*x);
  }
  
  @Override
  public void update(){
  }
}