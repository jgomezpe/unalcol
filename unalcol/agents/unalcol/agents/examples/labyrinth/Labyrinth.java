package unalcol.agents.examples.labyrinth;

import unalcol.agents.simulate.util.*;
import unalcol.agents.*;
import unalcol.agents.simulate.*;
import unalcol.collection.Vector;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.FileWriter;
import unalcol.agents.simulate.gui.*;

public class Labyrinth extends Environment{
    public static int DEFAULT_SIZE = 20;
  protected static final int F = 1<<0;
  protected static final int R = 1<<1;
  protected static final int B = 1<<2;
  protected static final int L = 1<<3;

  public static final String D = "direction";
  public static final String X = "column";
  public static final String Y = "row";
  public static String msg = null;
  public int[][] structure = null;
  
  protected Vector<Agent> failAgents = new Vector<Agent>();
  protected String[] available_actions = {LabyrinthUtil.NOP, LabyrinthUtil.DIE, LabyrinthUtil.ADVANCE, LabyrinthUtil.ROTATE};
  

  public int getRowsNumber(){  return structure.length; }
  public int getColumnsNumber(){  return structure[0].length; }
  
	protected Agent failAgent( int k ){
		try{ return failAgents.get(k); }catch(Exception e){ return null; }
	}  
  
  public boolean act(Agent agent, Action action){
	boolean fail = false;
    boolean flag = (action!=null);
    SimulatedAgent a = (SimulatedAgent)agent;
    if( flag ){
        if( delay > 0 ){
          try{
            agent.sleep(delay);
          }catch(Exception e ){}
        }
        String act = action.getCode();
        int direction = ((Integer) a.getAttribute(D)).intValue();
        int x = ((Integer) a.getAttribute(X)).intValue();
        int y = ((Integer) a.getAttribute(Y)).intValue();
        Percept p = sense(a);
//        System.out.println(p);
        String msg = null;
        switch (Language.getIndex(available_actions,act)) {
        case 0: // no_op
            break;
        case 1: // die
//            System.out.println("Die");
            a.die();
            break;
        case 2: // advance
            if (!((Boolean) p.getAttribute("front")).
                booleanValue() &&
                ( p.getAttribute("afront") == null || !((Boolean) p.getAttribute("afront")).
                booleanValue() ) ) {
                switch (direction) {
                case 0:
                    y--;
                    a.setAttribute(Y, new Integer(y));
                    break;
                case 1:
                    x++;
                    a.setAttribute(X, new Integer(x));
                    break;
                case 2:
                    y++;
                    a.setAttribute(Y, new Integer(y));
                    break;
                case 3:
                    x--;
                    a.setAttribute(X, new Integer(x));
                    break;
                }
            } else {
                msg = SimpleView.ERROR +
                      "[There is a wall/agent in front of mine (" + agent.getProgram().getClass().getSimpleName() +"). Action " + act +
                      " not executed]";
                fail = true;
            }
            break;
        case 3: // rotate
            direction = (direction + 1) % 4;
            a.setAttribute(D, new Integer(direction));
            break;
        default:
            msg = SimpleView.ERROR + "[Unknown action " + act +
                  ". Action not executed]";
            break;
        }
        updateViews(msg);
    }
    if( fail ){
        int i=0; 
        while( i<failAgents.size() && failAgent(i) != agent ){ i++; }
        if( i==failAgents.size() ){ failAgents.add(agent); }
    }else{
      int i=0; 
      while( i<failAgents.size() && failAgent(i) != agent ){ i++; }
      if( i<failAgents.size() ){ failAgents.remove(i); }
    }
    return flag;
  }

  protected LabyrinthPercept getPercept( int x, int y ){
      if( x >= 0 && x<structure.length && y >=0 && y<structure[0].length ) return new LabyrinthPercept( structure[x][y] );
      return new LabyrinthPercept(0);
  }

  public Percept sense(Agent agent){
    SimulatedAgent anAgent = (SimulatedAgent)agent;
    int direction = ((Integer)anAgent.getAttribute(D)).intValue();
    int x = ((Integer)anAgent.getAttribute(X)).intValue();
    int y = ((Integer)anAgent.getAttribute(Y)).intValue();
    LabyrinthPercept p = getPercept( x, y );
    for( int i=0; i<direction; i++ ){ p.rotate(); }
    int i=0;
    while( i<failAgents.size() && failAgent(i) != agent ){ i++; }
    p.setAttribute("fail", i<failAgents.size());
    return p;
  }

  public Labyrinth( Vector<Agent> _agents, int[][] _structure ) {
    super( _agents );
    for( Agent a:agents ){
       ((SimulatedAgent)a).setAttribute(D, 0);
       ((SimulatedAgent)a).setAttribute(X,0);
       ((SimulatedAgent)a).setAttribute(Y,0);
    }  
    structure = _structure;
  }

  public Labyrinth( Agent agent, int[][] _structure ) {
    super( agent );
    structure = _structure;
  }

  public Labyrinth copy(){
    return new Labyrinth( agents, structure.clone() );
  }

  public Vector<Action> actions(){
    Vector<Action> acts = new Vector<Action>();
    int n = available_actions.length;
    for( int i=0; i<n; i++ ){
      acts.add( new Action( available_actions[i] ) );
    }
    return acts;
  }

  public static int[][] read( String fileName ){
    try {
      FileReader file = new FileReader(fileName);
      StreamTokenizer tok = new StreamTokenizer( file );
      tok.nextToken();
      int n = (int)tok.nval;
      tok.nextToken();
      int m = (int)tok.nval;
      int[][] structure = new int[n][m];
      for( int i=0; i<n; i++ ){
        for( int j=0; j<m; j++ ){
          tok.nextToken();
          structure[i][j] = (int)tok.nval;
        }
      }
      msg = null;
      return structure;
    }catch (Exception e) {
      msg = e.getMessage();
      return null;
    }
  }

  public void load( String fileName ){
    structure = read( fileName );
  }

  public static void save( String fileName, int[][] structure ){
    try {
      FileWriter file = new FileWriter(fileName);
      int n = structure.length;
      int m = structure[0].length;
      file.write(""+n);
      file.write(" ");
      file.write(""+m);
      file.write("\n");
      for( int i=0; i<n; i++ ){
        for( int j=0; j<m; j++ ){
          file.write(""+structure[i][j]);
          file.write(" ");
        }
        file.write("\n");
      }
      file.close();
      msg = null;
    }catch (Exception e) {
      msg = e.getMessage();
    }
  }

  public void save( String fileName ){
    save( fileName, structure );
  }

  public boolean edit( int X, int Y ){
    int ROWS = getRowsNumber();
    int COLUMNS = getColumnsNumber();
    boolean flag = false;
    X -= LabyrinthDrawer.MARGIN;
    Y -= LabyrinthDrawer.MARGIN;
    int x = X/LabyrinthDrawer.CELL_SIZE;
    int y = Y/LabyrinthDrawer.CELL_SIZE;
    int x1 = X - x*LabyrinthDrawer.CELL_SIZE;
    int y1 = Y - y*LabyrinthDrawer.CELL_SIZE;
    if( x1 <= y1 ){
      if( x1 < LabyrinthDrawer.CELL_SIZE / 4 ){
        if( x < COLUMNS ) structure[x][y] ^= L;
        if( x > 0 )  structure[x-1][y] ^= R;
        flag = true;
      }else{
        if( x1 > 3*LabyrinthDrawer.CELL_SIZE / 4 ){
          structure[x][y] ^= R;
          if (x < COLUMNS-1)  structure[x+1][y] ^= L;
          flag = true;
        }
      }
    }else{
      if( y1 < LabyrinthDrawer.CELL_SIZE / 4 ){
        if( y < ROWS ) structure[x][y] ^= F;
        if( y > 0 )  structure[x][y-1] ^= B;
        flag = true;
      }else{
        if( y1 > 3 * LabyrinthDrawer.CELL_SIZE / 4 ){
          structure[x][y] ^= B;
          if (y < ROWS-1) {
            structure[x][y+1] ^= F;
          }
          flag = true;
        }
      }
    }
    return flag;
  }

  public void setAgentPosition( int agent_id, int x, int y, int d ){
    SimulatedAgent agent = (SimulatedAgent)getAgent(agent_id);
    agent.setAttribute(D, new Integer(d));
    agent.setAttribute(X, new Integer(x));
    agent.setAttribute(Y, new Integer(y));
    agent.status = Action.CONTINUE;
    init( agent );
  }

  public void setAgentPosition( SimulatedAgent agent, int x, int y, int d ){
    agent.setAttribute(D, new Integer(d));
    agent.setAttribute(X, new Integer(x));
    agent.setAttribute(Y, new Integer(y));
    agent.status = Action.CONTINUE;
    init( agent );
  }

  public void init( Agent agent ){
    //@TODO: Any special initialization process of the environment
    //SimulatedAgent sim_agent = (SimulatedAgent)agent;
  }
}
