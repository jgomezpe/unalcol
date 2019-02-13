package unalcol.agents.examples.games.sokoban;

import java.awt.Color;
import java.awt.Graphics;

import unalcol.agents.simulate.SimulatedAgent;
import unalcol.agents.simulate.gui.Drawer;

public class SokobanBoardDrawer extends Drawer {
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
		if( environment != null && ((SokobanEnvironment)environment).board != null ){
			SokobanEnvironment   env = (SokobanEnvironment)environment;
			SokobanBoard b = env.board;
			int n = b.rows();
			int m = b.columns();
			DIMENSION = Math.max(n, m);
		    CELL_SIZE = DRAW_AREA_SIZE / (DIMENSION+1);
		    MARGIN = CELL_SIZE / 2;
			g.setColor( Color.lightGray );
			int GRID_SIZE = CELL_SIZE * DIMENSION;
			for (int i = 0; i <= n; i++) {
				int j = getCanvasValue( i );
				g.drawLine( j, GRID_SIZE + MARGIN, j, MARGIN );
				g.drawLine( GRID_SIZE + MARGIN, j, MARGIN, j );
			}
			for (int i = 0; i < n; i++) {
				int X = getCanvasValue(i);
				for (int j = 0; j < m; j++) {
					int Y=getCanvasValue(j);
					int v = b.get(i, j);
					if((v&SokobanBoard.WALL)==SokobanBoard.WALL){
						g.setColor( Color.gray );
					        g.fillRect(X+1, Y+1, CELL_SIZE-2, CELL_SIZE-2);  
					}
					if((v&SokobanBoard.GRASS)==SokobanBoard.GRASS){
						g.setColor( Color.green );
					        g.fillRect(X+1, Y+1, CELL_SIZE-2, CELL_SIZE-2);  
					}
					if((v&SokobanBoard.MARK)==SokobanBoard.MARK){
						g.setColor( Color.yellow );
					        g.fillRect(X+CELL_SIZE/4, Y+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2);  
					}
					if((v&SokobanBoard.BLOCK)==SokobanBoard.BLOCK){
						if((v&SokobanBoard.MARK)==SokobanBoard.MARK) g.setColor( Color.cyan );
						else g.setColor( Color.pink );
					    g.fillRect(X+2, Y+2, CELL_SIZE-4, CELL_SIZE-4);  
					}
				}
			}
			SimulatedAgent a = (SimulatedAgent)env.getAgent();
			int direction = ((Integer) a.getAttribute(SokobanEnvironment.D)).intValue();
			int x = ((Integer) a.getAttribute(SokobanEnvironment.X)).intValue();
			int y = ((Integer) a.getAttribute(SokobanEnvironment.Y)).intValue();
			boolean play_mode = ((Boolean) a.getAttribute(SokobanUtil.MODE)).booleanValue();
			if( play_mode) g.setColor( Color.black ); else g.setColor(Color.lightGray);
			int X = getCanvasValue( x );
			int Y = getCanvasValue( y );
			int EYE_SIZE = 6;
			g.drawOval( X + EYE_SIZE/2, Y + EYE_SIZE/2, CELL_SIZE - EYE_SIZE, CELL_SIZE - EYE_SIZE );
			switch( direction ){
				case 0: g.drawOval( X + CELL_SIZE/2 - EYE_SIZE/2, Y , EYE_SIZE, EYE_SIZE); break;
				case 1: g.drawOval( X +  CELL_SIZE - EYE_SIZE, Y + CELL_SIZE/2 - EYE_SIZE/2, EYE_SIZE, EYE_SIZE); break;
				case 2: g.drawOval( X + CELL_SIZE/2 - EYE_SIZE/2, Y + CELL_SIZE - EYE_SIZE, EYE_SIZE, EYE_SIZE); break;
				case 3: g.drawOval( X, Y + CELL_SIZE/2 - EYE_SIZE/2, EYE_SIZE, EYE_SIZE); break;
			}
		}
	}

    @Override
    public void setDimension(int width, int height) {
	    DRAW_AREA_SIZE = Math.min(width,height);
    }
}