package unalcol.agents.examples.games.sokoban;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StreamTokenizer;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.Percept;
import unalcol.agents.simulate.Environment;
import unalcol.agents.simulate.SimulatedAgent;
import unalcol.agents.simulate.gui.SimpleView;
import unalcol.agents.simulate.util.Language;
import unalcol.collection.Vector;

public class SokobanEnvironment  extends Environment{
    
	protected String[] available_actions = {SokobanUtil.NOP, SokobanUtil.DIE, SokobanUtil.ADVANCE, SokobanUtil.ROTATE, SokobanUtil.PLAY};
	public static String msg;
        protected SokobanBoard board=null;
        
        protected static final int F = 1<<0;
        protected static final int R = 1<<1;
        protected static final int B = 1<<2;
        protected static final int L = 1<<3;

        public static final String D = "direction";
        public static final String X = "column";
        public static final String Y = "row";
       
        
	public SokobanEnvironment(Agent agent) {
		super(agent);
	}
	
	public SokobanEnvironment( Agent agent, SokobanBoard board ){
	    super(agent);
	    this.board = board;
	}
	
	public static int DEFAULT_SIZE = 20;
	
	public boolean can_go( int x, int y, int direction ){
		int ix = 0;
		int iy = 0;
		switch (direction) {
			case 0: iy--; break;
			case 1: ix++; break;
			case 2: iy++; break;
			case 3: ix--; break;
		}
		return( board.get(x+ix,y+iy) !=SokobanBoard.WALL && ((board.get(x+ix,y+iy)&SokobanBoard.BLOCK) !=SokobanBoard.BLOCK || board.get(x+2*ix,y+2*iy)<=SokobanBoard.MARK) );
	}
	
	public void go( SimulatedAgent a, int x, int y, int direction ){
		int ix = 0;
		int iy = 0;
		switch (direction) {
			case 0: iy--; break;
			case 1: ix++; break;
			case 2: iy++; break;
			case 3: ix--; break;
		}
		a.setAttribute(Y, new Integer(y+iy));
		a.setAttribute(X, new Integer(x+ix));
		if( (board.get(x+ix,y+iy)&SokobanBoard.BLOCK) == SokobanBoard.BLOCK ){
			board.xor(x+ix,y+iy,SokobanBoard.BLOCK);
			board.xor(x+2*ix,y+2*iy,SokobanBoard.BLOCK);			
		}
	}	

	public void shadow( SimulatedAgent a, int x, int y, int direction ){
		int ix = 0;
		int iy = 0;
		switch (direction) {
			case 0: iy--; break;
			case 1: ix++; break;
			case 2: iy++; break;
			case 3: ix--; break;
		}
		a.setAttribute(Y, new Integer(y+iy));
		a.setAttribute(X, new Integer(x+ix));
	}	
	
	public boolean act(Agent agent, Action action){
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
			boolean play_mode = ((Boolean) a.getAttribute(SokobanUtil.MODE)).booleanValue();
			Percept p = sense(a);
			String msg = null;
			switch (Language.getIndex(available_actions,act)) {
				case 0: // no_op
				break;
				case 1: // die
					a.die();
				break;
				case 2: // advance
					if( play_mode ){
						if ( can_go(x,y,direction) ) {
							go(a,x,y,direction);
						} else {
							msg = SimpleView.ERROR +
								"[There is a wall/fixed block in front of mine (" + agent.getProgram().getClass().getSimpleName() +"). Action " + act +
								" not executed]";
						}
					}else{
						if(!((Boolean) p.getAttribute("front")).booleanValue()) { 
						    shadow(a,x,y,direction);
						} else {
							msg = SimpleView.ERROR +
								"[There is a wall in front of mine (" + agent.getProgram().getClass().getSimpleName() +"). Action " + act +
								" not executed]";
						}
					}
				break;
				case 3: // rotate
					direction = (direction + 1) % 4;
					a.setAttribute(D, new Integer(direction));
				break;
				case 4: // play mode
					if( !play_mode ){
						setAgentPosition(sx, sy, 0);
						a.setAttribute(SokobanUtil.MODE, true);
					}
				break;    
				default:
					msg = SimpleView.ERROR + "[Unknown action " + act +
					". Action not executed]";
				break;
			}
			updateViews(msg);
		}
		return flag;
	}
	
	public void setAgentPosition( int x, int y, int d ){
		SimulatedAgent agent = (SimulatedAgent)getAgent();
		agent.setAttribute(D, new Integer(d));
		agent.setAttribute(X, new Integer(x));
		agent.setAttribute(Y, new Integer(y));
		agent.setAttribute(SokobanUtil.MODE, false);
		agent.status = Action.CONTINUE;
		sx = x;
		sy = y;
		init( agent );
	}
	
	private int sx, sy;
	
	public void init( Agent agent ){
	      //@TODO: Any special initialization process of the environment
	      //SimulatedAgent sim_agent = (SimulatedAgent)agent;
	}

	@Override
	public Percept sense(Agent agent) {
	    SimulatedAgent anAgent = (SimulatedAgent)agent;
	    int direction = ((Integer)anAgent.getAttribute(D)).intValue();
	    int x = ((Integer)anAgent.getAttribute(X)).intValue();
	    int y = ((Integer)anAgent.getAttribute(Y)).intValue();
	    SokobanPercept p = getPercept( x, y );
	    for( int i=0; i<direction; i++ ){ p.rotate(); }
	    return p;
	}

	 protected SokobanPercept getPercept( int x, int y ){
	      if( x >= 1 && x<board.rows()-1 && y >=1 && y<board.columns()-1 ) 
		  return new SokobanPercept( board.get(x-1,y), board.get(x, y-1), board.get(x+1,y), board.get(x,y+1), board.get(x, y) );
	      return null;
	  }
	 
	@Override
	public Vector<Action> actions() {
	    Vector<Action> acts = new Vector<Action>();
	    int n = available_actions.length;
	    for( int i=0; i<n; i++ ){
	      acts.add( new Action( available_actions[i] ) );
	    }
	    return acts;
	}
	
	public int[][] read( String fileName ){
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
			tok.nextToken();
			int x = (int)tok.nval;
			tok.nextToken();
			int y = (int)tok.nval;
			file.close();
			setAgentPosition(x, y, 0);
			System.out.println("("+x+","+y+")");
			msg = null;
			return structure;
		}catch (Exception e) {
			msg = e.getMessage();
			return null;
		}
	}

	public void load( String fileName ){
		board = new SokobanBoard(read( fileName ));
	}

	public void save( String fileName, SokobanBoard structure ){
		try {
			FileWriter file = new FileWriter(fileName);
			int n = structure.rows();
			int m = structure.columns();
			file.write(""+n);
			file.write(" ");
			file.write(""+m);
			file.write("\n");
			for( int i=0; i<n; i++ ){
				for( int j=0; j<m; j++ ){
					file.write(""+structure.get(i,j));
					file.write(" ");
				}
				file.write("\n");
			}
			SimulatedAgent a = (SimulatedAgent)getAgent(); 
			int x = ((Integer) a.getAttribute(X)).intValue();
			int y = ((Integer) a.getAttribute(Y)).intValue();
			file.write(""+x+" "+y);
			file.close();
			msg = null;
		}catch (Exception e) {
			msg = e.getMessage();
		}
	}

	public void save( String fileName ){
		save( fileName, board );
	}
	
	  public int getRowsNumber(){  return board.rows(); }
	  public int getColumnsNumber(){  return board.columns(); }

	public boolean edit( int X, int Y ){
		boolean flag = false;
		X -= SokobanBoardDrawer.MARGIN;
		Y -= SokobanBoardDrawer.MARGIN;
		int x = X/SokobanBoardDrawer.CELL_SIZE;
		int y = Y/SokobanBoardDrawer.CELL_SIZE;
		int x1 = X - x*SokobanBoardDrawer.CELL_SIZE;
		int y1 = Y - y*SokobanBoardDrawer.CELL_SIZE;
		if( SokobanBoardDrawer.CELL_SIZE/4<=x1 && x1<=3*SokobanBoardDrawer.CELL_SIZE/4  &&
			SokobanBoardDrawer.CELL_SIZE/4<=y1 && y1<=3*SokobanBoardDrawer.CELL_SIZE/4	){ 
		    setAgentPosition(x, y, 0);
		}else{
			if( x1 <= SokobanBoardDrawer.CELL_SIZE/2 ){  
				if( y1 < SokobanBoardDrawer.CELL_SIZE / 2 ){
					if( board.get(x, y) != SokobanBoard.WALL) board.reset(x, y);
					board.xor(x, y, SokobanBoard.WALL);
					flag = true;
				}else{
					if( board.get(x, y) != SokobanBoard.GRASS) board.reset(x, y);
					board.xor(x, y, SokobanBoard.GRASS);
					flag = true;
				}
			}else{
				if( y1 < SokobanBoardDrawer.CELL_SIZE / 2 ){
					flag = board.get(x, y) != SokobanBoard.WALL && board.get(x, y) != SokobanBoard.GRASS;
					if(flag) board.xor(x, y, SokobanBoard.BLOCK);
				}else{
					flag = board.get(x, y) != SokobanBoard.WALL && board.get(x, y) != SokobanBoard.GRASS;
					if(flag) board.xor(x, y, SokobanBoard.MARK);	       
				}
			}	
		}
		
		return flag;
	}
}
