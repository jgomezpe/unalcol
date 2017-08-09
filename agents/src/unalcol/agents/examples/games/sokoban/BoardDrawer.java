package unalcol.agents.examples.games.sokoban;

import java.awt.Color;
import java.awt.Graphics;

import unalcol.agents.examples.labyrinth.Labyrinth;
import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.simulate.gui.Drawer;

public class BoardDrawer extends Drawer {
    public static int DIMENSION = 15;
    public static int DRAW_AREA_SIZE = 400;
    public static int CELL_SIZE = 20;
    public static int MARGIN = 10;
    public static int SHAPE_SIZE = 1;
 
	protected int getCanvasValue( int val ){
		return val*CELL_SIZE+MARGIN;
	}

    @Override
    public void paint(Graphics g) {
	    if( environment != null && ((Labyrinth)environment).structure != null ){
		      Labyrinth   env = (Labyrinth)environment;
		      int n = env.structure.length;
		      int m = env.structure[0].length;
		      g.setColor( Color.lightGray );
		      int GRID_SIZE = CELL_SIZE * DIMENSION;
		      for (int i = 0; i <= n; i++) {
		        int j = getCanvasValue( i );
		        g.drawLine( j, GRID_SIZE + MARGIN, j, MARGIN );
		        g.drawLine( GRID_SIZE + MARGIN, j, MARGIN, j );
		      }
		      for (int i = 0; i < n; i++) {
		        for (int j = 0; j < m; j++) {
		          g.setColor( Color.blue );
		          LabyrinthPercept p = env.getPercept(i,j);
		          int x = getCanvasValue(i);
		          int y = getCanvasValue(j);
		          pDrawer.draw( g, x, y, CELL_SIZE, p );
		        }
		      }
    }

    @Override
    public void setDimension(int width, int height) {
	    DRAW_AREA_SIZE = Math.min(width,height);
	    CELL_SIZE = DRAW_AREA_SIZE / (DIMENSION+1);
	    MARGIN = CELL_SIZE / 2;
    }

}
