package unalcol.agents.simulate;

import unalcol.agents.*;
import unalcol.types.collection.vector.*;
import unalcol.agents.simulate.gui.*;

/**
 * <p>Title: Environment </p>
 *
 * <p>Description: The environment for the given agents problem </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class Environment extends Kernel implements AgentArchitecture{
  protected long delay = 0;
  protected Vector<EnvironmentView> views = new Vector<EnvironmentView>();

  public Environment( Agent agent ) {
    super(agent);
    int n = agents.size();
    for( int i=0; i<n; i++ ){
      agents.set( i, new SimulatedAgent( this, agents.get(i).getProgram()) );
    }
  }

  public Environment( Vector<Agent> _agents ) {
    super( _agents );
    int n = agents.size();
    for( int i=0; i<n; i++ ){
      agents.set( i, new SimulatedAgent( this, agents.get(i).getProgram()) );
    }
  }

  public void setDelay( long _delay ){
    delay = _delay;
  }

  public void registerView( EnvironmentView view ){
    if( !views.contains(view) ){
      views.add(view);
    }
  }

  public void updateViews( String message ){
     for( int i=0; i<views.size(); i++ ){
       views.get(i).envChanged( message );
     }
  }
  
  public int agentsNumber(){
      return agents.size();
  }
}
