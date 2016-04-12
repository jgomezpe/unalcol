package unalcol.agents.examples.labyrinth.teseoeater;
import unalcol.agents.examples.labyrinth.*;

import unalcol.agents.*;
import unalcol.agents.simulate.*;
import unalcol.agents.simulate.util.*;

import unalcol.types.collection.vector.*;
import unalcol.agents.simulate.gui.*;

class Key{
  protected String color;
  protected String shape;
  public Key( String _color, String _shape ){
    color = _color;
    shape = _shape;
  }
}

public class TeseoEaterLabyrinth extends Labyrinth {
  public static final int AGENT = 0;
  public static final int TREASURE = 1;
  public static final int RESOURCE = 2;

  protected static String K = "key";

  protected int option = AGENT;
  protected int tx = 0;
  protected int ty = 0;
  protected int resource = 0;

  public static final int MAX_ENERGY_LEVEL = 40;
  public static final int INC_ENERGY_LEVEL = 10;
  public static final int DEC_ENERGY_LEVEL = 5;

  public static final int INITIAL_ENERGY = 20;
  protected int energy_level = INITIAL_ENERGY;

  protected LabyrinthPercept getPercept( int x, int y ){
    LabyrinthPercept perc = new TeseoEaterPercept( structure[x][y] );
    perc.setAttribute(LabyrinthUtil.ENERGY, energy_level);
    return perc;
  }

  public TeseoEaterLabyrinth( Agent agent, int[][] _structure ) {
    super( agent, _structure );
  }

  public TeseoEaterLabyrinth( Vector<Agent> _agents, int[][] _structure ) {
    super( _agents, _structure );
    available_actions = new String[]{LabyrinthUtil.NOP, LabyrinthUtil.DIE, LabyrinthUtil.ADVANCE, LabyrinthUtil.ROTATE, 
	    LabyrinthUtil.EAT};
    
  }

  public void init( Agent agent ){
    //@TODO: Any special initialization processs of the environment
    //SimulatedAgent sim_agent = (SimulatedAgent)agent;
    energy_level = INITIAL_ENERGY;
  }

  public boolean act( Agent agent, Action action ){
    if( energy_level <= 0 ){
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
              energy_level = Math.min( energy_level + INC_ENERGY_LEVEL, MAX_ENERGY_LEVEL );
            }else{
              System.out.println("Eating bad food...");
              energy_level = Math.max( energy_level - DEC_ENERGY_LEVEL, 0 );
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
            energy_level--;
            System.out.println( energy_level );
          }
          super.act(agent, action);
          break;
      }
    }
    return flag;
  }

  public void setOption( int _option ){ option = _option; }

  public void setResource( boolean _shape, boolean _color, boolean _size,
                           boolean _weight, boolean _healthy ){
    int ONES_MASK = (0xFFFFFFFF);
    int color = _color?ONES_MASK:~(1<<6);
    int shape = _shape?ONES_MASK:~(1<<7);
    int size = _size?ONES_MASK:~(1<<8);
    int weight = _weight?ONES_MASK:~(1<<9);
    int healthy = _healthy?ONES_MASK:~(1<<10);
    resource = color & shape & size & weight & healthy;
  }

  public boolean edit( int X, int Y ){
    boolean flag = super.edit(X,Y);
    if( !flag ){
      X -= LabyrinthDrawer.MARGIN;
      Y -= LabyrinthDrawer.MARGIN;
      int x = X/LabyrinthDrawer.CELL_SIZE;
      int y = Y/LabyrinthDrawer.CELL_SIZE;
      switch( option ){
        case AGENT:
          setAgentPosition( 0, x, y, 0 );
        break;
        case TREASURE:
          if( (structure[tx][ty] & (1<<4)) == (1<<4) )
            structure[tx][ty] ^= (1<<4);
          structure[x][y] ^= (1<<4);
          tx = x;
          ty = y;
        break;
        case RESOURCE:
          structure[x][y] ^= (1<<5);
          structure[x][y] |= (31<<6);
          structure[x][y] &= resource;
        break;
      }
    }
    return flag;
  }
}
