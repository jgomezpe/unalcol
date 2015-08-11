package unalcol.agents.examples.labyrinth.teseo.simple;

import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.*;
import unalcol.agents.Action;

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
public abstract class SimpleTeseoAgentProgram  implements AgentProgram{
  protected SimpleLanguage language;
  protected Vector<String> cmd = new Vector<String>();
  public SimpleTeseoAgentProgram( ) {
  }

  public void setLanguage(  SimpleLanguage _language ){
    language = _language;
  }

  public void init(){
    cmd.clear();
  }

  public abstract int accion( boolean PF, boolean PD, boolean PA, boolean PI, boolean MT );

  /**
   * execute
   *
   * @param perception Perception
   * @return Action[]
   */
  public Action compute(Percept p){
    if( cmd.size() == 0 ){

      boolean PF = ( (Boolean) p.getAttribute(language.getPercept(0))).
          booleanValue();
      boolean PD = ( (Boolean) p.getAttribute(language.getPercept(1))).
          booleanValue();
      boolean PA = ( (Boolean) p.getAttribute(language.getPercept(2))).
          booleanValue();
      boolean PI = ( (Boolean) p.getAttribute(language.getPercept(3))).
          booleanValue();
      boolean MT = ( (Boolean) p.getAttribute(language.getPercept(4))).
          booleanValue();

      int d = accion(PF, PD, PA, PI, MT);
      if (0 <= d && d < 4) {
        for (int i = 1; i <= d; i++) {
          cmd.add(language.getAction(3)); //rotate
        }
        cmd.add(language.getAction(2)); // advance
      }
      else {
        cmd.add(language.getAction(0)); // die
      }
    }
    String x = cmd.get(0);
    cmd.remove(0);
    return new Action(x);
  }

  /**
   * goalAchieved
   *
   * @param perception Perception
   * @return boolean
   */
  public boolean goalAchieved( Percept p ){
    return (((Boolean)p.getAttribute(language.getPercept(4))).booleanValue());
  }
}
