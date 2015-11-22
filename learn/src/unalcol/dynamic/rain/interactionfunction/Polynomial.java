package unalcol.dynamic.rain.interactionfunction;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Polynomial implements InteractionFunction {

  protected double pow = 3.0;

  public Polynomial( double _pow ){
    pow = _pow;
  }

  @Override
  public double evaluate( double x ){
    return Math.pow( x, pow);
  }

  @Override
  public void update(){}
}