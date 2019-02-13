package unalcol.agents.simulate;

import unalcol.agents.*;
import unalcol.collection.Vector;
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
		Vector<Agent> newagents = new Vector<Agent>();
		for( Agent a: agents ) newagents.add( new SimulatedAgent( this, a.getProgram()) );
		agents = newagents;
	}

	public Environment( Vector<Agent> _agents ) {
		super( _agents );
		Vector<Agent> newagents = new Vector<Agent>();
		for( Agent a: agents ) newagents.add( new SimulatedAgent( this, a.getProgram()) );
		agents = newagents;
	}

	public void setDelay( long _delay ){ delay = _delay; }

	public void registerView( EnvironmentView view ){ if( !views.contains(view) ) views.add(view); }

	public void updateViews( String message ){ for( EnvironmentView v:views ) v.envChanged( message ); }
  
	public int agentsNumber(){ return agents.size(); }
	
	protected Agent agent( int k ){
		try{ return agents.get(k); }catch(Exception e){ return null; }
	}
}