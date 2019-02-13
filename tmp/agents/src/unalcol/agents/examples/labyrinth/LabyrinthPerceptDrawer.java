package unalcol.agents.examples.labyrinth;

import java.awt.Graphics;

import unalcol.agents.Percept;

public class LabyrinthPerceptDrawer {
    public void draw( Graphics g, int x, int y, int CELL_SIZE, Percept p ){
	if( ((Boolean)p.getAttribute(LabyrinthUtil.WALL[0])).booleanValue() ){ g.drawLine(x,y,x+CELL_SIZE,y); }
	if( ((Boolean)p.getAttribute(LabyrinthUtil.WALL[1])).booleanValue() ){ g.drawLine(x+CELL_SIZE,y,x+CELL_SIZE,y+CELL_SIZE); }    
	if( ((Boolean)p.getAttribute(LabyrinthUtil.WALL[2])).booleanValue() ){ g.drawLine(x,y+CELL_SIZE,x+CELL_SIZE,y+CELL_SIZE); }
	if( ((Boolean)p.getAttribute(LabyrinthUtil.WALL[3])).booleanValue() ){ g.drawLine(x,y,x,y+CELL_SIZE); }
    }
}