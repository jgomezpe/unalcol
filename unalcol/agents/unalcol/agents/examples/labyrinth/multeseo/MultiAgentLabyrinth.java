package unalcol.agents.examples.labyrinth.multeseo;
import unalcol.agents.examples.labyrinth.*;

import unalcol.agents.*;
import unalcol.agents.simulate.*;
import unalcol.collection.Vector;

class Key{
  protected String color;
  protected String shape;
  public Key( String _color, String _shape ){
    color = _color;
    shape = _shape;
  }
}

public class MultiAgentLabyrinth extends Labyrinth {
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
    for( int i=0; i<LabyrinthUtil.AGENT.length; i++){
	p.setAttribute(LabyrinthUtil.AGENT[i], false);
    }
    int[][] pos = new int[][]{{x,x+1,x,x-1},{y-1,y,y+1,y}};
    for( Agent ag:agents ){
        if( ag != agent ){
            SimulatedAgent a = (SimulatedAgent)ag;               
            int ax = ((Integer) a.getAttribute(X)).intValue();
            int ay = ((Integer) a.getAttribute(Y)).intValue();
            for( int k=0; k<LabyrinthUtil.AGENT.length; k++ ){
        		if( !((Boolean)p.getAttribute(LabyrinthUtil.WALL[k])).booleanValue() && 
                      pos[1][k] == ay && pos[0][k]==ax  )
        		    p.setAttribute(LabyrinthUtil.AGENT[k], true);
            }
        }    
    }
    for( int i=0; i<direction; i++ ){ p.rotate(); }
    int i=0;
    while( i<failAgents.size() && failAgent(i) != agent ){ i++; }
    p.setAttribute(LabyrinthUtil.FAIL, i<failAgents.size());
    return p;
  }

  public MultiAgentLabyrinth( Vector<Agent> _agents, int[][] _structure ) {
    super( _agents, _structure );
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
