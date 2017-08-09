package unalcol.agents.examples.games.sokoban;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.LabyrinthUtil;
import unalcol.agents.simulate.Environment;
import unalcol.agents.simulate.SimulatedAgent;
import unalcol.agents.simulate.gui.SimpleView;
import unalcol.agents.simulate.util.Language;
import unalcol.types.collection.vector.Vector;

public class SokobanEnvironment  extends Environment{
    
	protected String[] available_actions = {SokobanUtil.NOP, SokobanUtil.DIE, SokobanUtil.ADVANCE, SokobanUtil.ROTATE, SokobanUtil.SHADOW_ADVANCE};
	
        protected Board board=null;
        
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
	
	public SokobanEnvironment( Agent agent, Board board ){
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
		return( board.data[x+ix][y+iy] !=Board.WALL && (board.data[x+ix][y+iy] !=Board.BLOCK||board.data[x+2*ix][y+2*iy]<=Board.MARK) );
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
		if(board.data[x+ix][y+iy] ==Board.BLOCK){
			board.xor(x+ix,y+iy,Board.BLOCK);
			board.xor(x+2*ix,y+2*iy,Board.BLOCK);			
		}
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
			String msg = null;
			switch (Language.getIndex(available_actions,act)) {
				case 0: // no_op
				break;
				case 1: // die
					a.die();
				break;
				case 2: // advance
					a.setAttribute(SokobanUtil.MODE, true);
					if ( can_go(x,y,direction) ) {
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
				case 4: // shadow advance
					fail = !((Boolean) a.getAttribute(X)).booleanValue() && !((Boolean) p.getAttribute("front")).booleanValue();					
					if(!fail) {
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
				default:
					msg = SimpleView.ERROR + "[Unknown action " + act +
					". Action not executed]";
				break;
			}
			updateViews(msg);
		}
		return flag;
	}
	
	public void setAgentPosition( SimulatedAgent agent, int x, int y, int d ){
		agent.setAttribute(D, new Integer(d));
		agent.setAttribute(X, new Integer(x));
		agent.setAttribute(Y, new Integer(y));
		agent.setAttribute(SokobanUtil.MODE, false);
		agent.status = Action.CONTINUE;
		init( agent );
	}
	
	public void init( Agent agent ){
	      //@TODO: Any special initialization process of the environment
	      //SimulatedAgent sim_agent = (SimulatedAgent)agent;
	}

	@Override
	public Percept sense(Agent agent) {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public Vector<Action> actions() {
	    // TODO Auto-generated method stub
	    return null;
	}
}
