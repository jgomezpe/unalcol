package unalcol.dynamic.rain.interactionfunction;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public interface InteractionFunction {
  public double evaluate( double x );
  public void update();
}