package unalcol.agents.examples.labyrinth.variable;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.examples.labyrinth.Labyrinth;
import unalcol.agents.examples.labyrinth.LabyrinthDrawer;
import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.examples.labyrinth.teseo.TeseoPercept;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.random.raw.RawGenerator;
import unalcol.types.collection.vector.Vector;

public class TeseoVariableLabyrinth  extends Labyrinth {
	protected int x = -1;
	protected int y;
	protected boolean flag = false;

	  protected static Vector<Agent> add( Agent agent ){
		  Vector<Agent> v = new Vector<Agent>();
		  v.add(agent);
		  return v;
	  }
	  
	  protected static Vector<Agent> add( double p, Vector<Agent> v ){
		  v.add( new Agent( new WallDaemon(p) ) );
		  return v;
	  }
	  
	  protected LabyrinthPercept getPercept( int x, int y ){
	    return new TeseoPercept( structure[x][y], language );
	  }

	  public TeseoVariableLabyrinth( Vector<Agent> _agents, int[][] _structure, SimpleLanguage _language, double p ) {
	    super( add(p, _agents), _structure, _language );
	  }

	  public TeseoVariableLabyrinth( Agent agent, int[][] _structure, SimpleLanguage _language, double p ){
	    this( add(agent), _structure, _language, p );
	  }

	  public Labyrinth copy(){
		  double p = 0.0;
		  int i=0;
		  while( i<agents.size() && !(agents.get(i).getProgram() instanceof WallDaemon) ){
			  i++;
		  }
		  if( i<agents.size() ){
			  WallDaemon daemon = (WallDaemon)agents.get(i).getProgram();
			  p = daemon.probability;
		  }
	    return new TeseoVariableLabyrinth( agents, structure.clone(), language, p );
	  }

	  public boolean edit( int X, int Y ){
	    boolean flag = super.edit(X,Y);
	    if( !flag ){
	      X -= LabyrinthDrawer.MARGIN;
	      Y -= LabyrinthDrawer.MARGIN;
	      int x = X/LabyrinthDrawer.CELL_SIZE;
	      int y = Y/LabyrinthDrawer.CELL_SIZE;
	      if( 0<=x && x<LabyrinthDrawer.DIMENSION && 0<=y && y<LabyrinthDrawer.DIMENSION ){
	        structure[x][y] ^= (1 << 4);
	        flag = true;
	      }else{
	        flag = false;
	      }
	    }
	    return flag;
	  }

	  public boolean act(Agent agent, Action action){
		if( agent.getProgram() instanceof WallDaemon ){
		  if( action.getCode()=="change_walls"){
			int COLUMNS = getColumnsNumber();
			int ROWS = getRowsNumber();
			if( x >= 0 ){
	  		  if( flag ){
			    if( x < COLUMNS ) structure[x][y] ^= L;
		        if( x > 0 )  structure[x-1][y] ^= R;
			  }else{
	            if( y < ROWS ) structure[x][y] ^= F;
	            if( y > 0 )  structure[x][y-1] ^= B;				
		      }
			}
			x = RawGenerator.integer(this, COLUMNS);
		    y = RawGenerator.integer(this, ROWS);
			flag = RawGenerator.bool(this);
			if( flag ){
			  if( x < COLUMNS ) structure[x][y] ^= L;
		      if( x > 0 )  structure[x-1][y] ^= R;
		    }else{
	          if( y < ROWS ) structure[x][y] ^= F;
		      if( y > 0 )  structure[x][y-1] ^= B;				
			}
		  }	
	  	  return true;
		}else{
		  return super.act(agent, action);
		}  	  
	  }
}