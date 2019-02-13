package unalcol.agents.examples.labyrinth.multeseo.eater;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.examples.labyrinth.LabyrinthUtil;
import unalcol.agents.examples.labyrinth.multeseo.MultiAgentLabyrinth;
import unalcol.agents.simulate.SimulatedAgent;
import unalcol.agents.simulate.gui.SimpleView;
import unalcol.agents.simulate.util.Language;
import unalcol.collection.Vector;

public class MultiTeseoEaterLabyrinth extends MultiAgentLabyrinth{
    public static final int AGENT = 0;
    public static final int TREASURE = 1;
    public static final int RESOURCE = 2;

    protected static String K = "key";

    protected int option = AGENT;
    protected int tx = 0;
    protected int ty = 0;
    protected int agent_id = 0;
    protected int resource = 0;

    public static final int MAX_ENERGY_LEVEL = 40;
    public static final int INC_ENERGY_LEVEL = 10;
    public static final int DEC_ENERGY_LEVEL = 5;

    public static final int INITIAL_ENERGY = 20;
 
    protected int[]  agent_energy_level;
    
    public MultiTeseoEaterLabyrinth(Vector<Agent> _agents, int[][] _structure) {
	super(_agents, _structure);
	available_actions = new String[]{LabyrinthUtil.NOP, LabyrinthUtil.DIE, LabyrinthUtil.ADVANCE, LabyrinthUtil.ROTATE, 
		    LabyrinthUtil.EAT};
	agent_energy_level = new int[_agents.size()];
	for( int i=0; i<agent_energy_level.length; i++ ){
	    agent_energy_level[i] = INITIAL_ENERGY;
	}
    }   
    
    protected int agentIndex( Agent agent ){
	int i=0;
	while( i<agents.size() && agent(i)!=agent) i++;
	return i;
    }

    public void init( Agent agent ){
	    //@TODO: Any special initialization processs of the environment
	    //SimulatedAgent sim_agent = (SimulatedAgent)agent;
	int i = agentIndex( agent );
	if( i<agents.size() ) agent_energy_level[i] = INITIAL_ENERGY;
    }
   
    protected LabyrinthPercept getPercept( int x, int y ){
	    return new MultiTeseoEaterPercept( structure[x][y] );
    }
    
    public Percept sense(Agent agent){
	Percept p = super.sense(agent);
	int i=agentIndex(agent);
	p.setAttribute(LabyrinthUtil.ENERGY, agent_energy_level[i]);
	return p;
    }   
    
    public boolean act( Agent agent, Action action ){
	int i = agentIndex(agent);
	if( i>=agents.size()) return false;
	    if( agent_energy_level[i] <= 0 ){
	      agent.die();
	      updateViews(msg);
	      return false;
	    }
	    boolean flag = (action != null);
	    if( flag ){
	      SimulatedAgent a = (SimulatedAgent) agent;
	      int x = ( (Integer) a.getAttribute(X)).intValue();
	      int y = ( (Integer) a.getAttribute(Y)).intValue();
	      Percept p = sense(a);
	      String msg = null;
	      String act = action.getCode();
	      int actionID = Language.getIndex(available_actions,act);
	      switch (actionID) {
	        case 4: // eat
	          if ( ( (Boolean) p.getAttribute(LabyrinthUtil.RESOURCE[0])).booleanValue()) {
	            // @TODO: Update the perception goodness
	            //structure[x][y] |= (1<<15);
	            int bit_flag = (1<<10);
	            if( (structure[x][y] & bit_flag) == bit_flag ){
	              System.out.println("Eating good food...");
	              agent_energy_level[i] = Math.min( agent_energy_level[i] + INC_ENERGY_LEVEL, MAX_ENERGY_LEVEL );
	            }else{
	              System.out.println("Eating bad food...");
	              agent_energy_level[i] = Math.max( agent_energy_level[i] - DEC_ENERGY_LEVEL, 0 );
	            }
	          }
	          else {
	            msg = SimpleView.ERROR +
	                "[There is not food. Eat action not executed]";
	          }
	          updateViews(msg);
	          break;
	        default:
	          if( actionID == 2 ){
	            agent_energy_level[i]--;
	          }
	          if( actionID != 2 || !((Boolean) p.getAttribute("afront")).booleanValue() ) super.act(agent, action);
	          else {
	                msg = SimpleView.ERROR +
	                      "[There is a wall/agent in front of mine (" + agent.getProgram().getClass().getSimpleName() +"). Action " + act +
	                      " not executed]";
	                updateViews(msg);
	            }
	          break;
	      }
	    }
	    return flag;
	  }
    
}