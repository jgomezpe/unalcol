package unalcol.agents.examples.labyrinth.variable;

import unalcol.agents.Agent;
import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.LabyrinthDrawer;
import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.examples.labyrinth.multeseo.MultiAgentLabyrinthPercept;
import unalcol.agents.simulate.SimulatedAgent;
import unalcol.collection.Vector;

public class MultiTeseoVariableLabyrinth  extends TeseoVariableLabyrinth {
	  public static final int AGENT = 0;
	  public static final int TREASURE = 1;
	  public static final int RESOURCE = 2;

	  protected static String K = "key";

	  protected int option = AGENT;
	  protected int tx = 0;
	  protected int ty = 0;
	  protected int agent_id = 0;

	  @Override
	  protected LabyrinthPercept getPercept( int x, int y ){
        if( x >= 0 && x<structure.length && y >=0 && y<structure[0].length )
	      return new MultiAgentLabyrinthPercept( structure[x][y] );
        return new MultiAgentLabyrinthPercept( 0 );     
	  }

	  public Percept sense(Agent agent){
	    SimulatedAgent anAgent = (SimulatedAgent)agent;
	    int direction = ((Integer)anAgent.getAttribute(D)).intValue();
	    int x = ((Integer)anAgent.getAttribute(X)).intValue();
	    int y = ((Integer)anAgent.getAttribute(Y)).intValue();
	    LabyrinthPercept p = getPercept( x, y );
	    p.setAttribute("afront", false);
	    p.setAttribute("aleft", false);
	    p.setAttribute("aright", false);
	    p.setAttribute("aback", false);
	    for( Agent ag:agents ){
	        if( ag != agent ){
	            SimulatedAgent a = (SimulatedAgent)ag;               
	            int ax = ((Integer) a.getAttribute(X)).intValue();
	            int ay = ((Integer) a.getAttribute(Y)).intValue();
	            //System.out.println("("+x+","+y+") : ("+ax+","+ay+")");
	            if( !((Boolean)p.getAttribute("front")).booleanValue() && 
	                 y-1 == ay && x==ax  ){
	                p.setAttribute("afront", true);
	            }
	            if( !((Boolean)p.getAttribute("right")).booleanValue() && 
	                 x+1 == ax && y==ay ){
	                p.setAttribute("aright", true);
	            }
	            if( !((Boolean)p.getAttribute("back")).booleanValue() && 
	                 y+1 == ay && x==ax ){
	                p.setAttribute("aback", true);
	            }
	            if( !((Boolean)p.getAttribute("left")).booleanValue() && 
	                 x-1 == ax && y==ay ){
	                p.setAttribute("aleft", true);
	            }
	        }    
	    }
	    for( int i=0; i<direction; i++ ){ p.rotate(); }
	    int i=0;
	    while( i<failAgents.size() && failAgent(i) != agent ){ i++; }
	    p.setAttribute("fail", i<failAgents.size());
	    return p;
	  }

	  public MultiTeseoVariableLabyrinth( Vector<Agent> _agents, int[][] _structure, double p ) {
	    super( _agents, _structure, p );
	  }


	  public void setOption( int _option ){ option = _option; }

	  @Override
	  public boolean edit( int X, int Y ){
	    boolean flag = super.edit(X,Y);
	    if( !flag ){
	      X -= LabyrinthDrawer.MARGIN;
	      Y -= LabyrinthDrawer.MARGIN;
	      int x = X/LabyrinthDrawer.CELL_SIZE;
	      int y = Y/LabyrinthDrawer.CELL_SIZE;
	      switch( option ){
	        case AGENT:
	          setAgentPosition( agent_id, x, y, 0 );
	        break;
	        case TREASURE:
	          if( (structure[tx][ty] & (1<<4)) == (1<<4) )
	            structure[tx][ty] ^= (1<<4);
	          structure[x][y] ^= (1<<4);
	          tx = x;
	          ty = y;
	        break;
	      }
	    }
	    return flag;
	  }
}
