package unalcol.agents.simulate.gui;



/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public interface EnvironmentView {
  public static final String DONE = "Done";
  public static final String ERROR = "Error";
  public void envChanged( String command );
}
