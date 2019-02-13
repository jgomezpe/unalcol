package unalcol.agents.examples.labyrinth.teseoeater;

import java.awt.Graphics;

import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.LabyrinthPerceptDrawer;
import unalcol.agents.examples.labyrinth.LabyrinthUtil;

public class TeseoEaterPerceptDrawer extends LabyrinthPerceptDrawer{
    @Override
    public void draw( Graphics g, int x, int y, int CELL_SIZE, Percept p ){
	    super.draw(g, x, y, CELL_SIZE, p );
	    if( ((Boolean)p.getAttribute(LabyrinthUtil.TREASURE)).booleanValue() ){
		// treasure
	      g.drawLine(x,y,x+CELL_SIZE,y+CELL_SIZE);
	      g.drawLine(x,y+CELL_SIZE,x+CELL_SIZE,y);
	    }

	    drawFood( g, x, y, CELL_SIZE, p );
    }
    
    protected void drawFood( Graphics g, int x, int y, int CELL_SIZE, Percept p ){
	    if( ((Boolean)p.getAttribute(LabyrinthUtil.RESOURCE[0])).booleanValue() ){ // key or lock
	      int DELTA = CELL_SIZE / 8;
	      int dx = DELTA;
	      for( int k=1; k<=4; k++ ){
	          if( ((Boolean)p.getAttribute(LabyrinthUtil.RESOURCE[k])).booleanValue() ){ // key or lock
	              g.drawLine(x + dx, y + 2, x + dx, y + 2*DELTA);
	          }
	          dx += DELTA;
	      }
	      dx += DELTA;
	    }
	  }
}
