/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.squares;

import java.awt.Graphics;
import unalcol.agents.simulate.Environment;
import unalcol.agents.simulate.gui.Drawer;

/**
 *
 * @author Jonatan
 */
public class SquaresDrawer  extends Drawer{
    public static int DRAW_AREA_SIZE = 180;
    public static int CELL_SIZE = 20;
    public static int MARGIN = 10;
    
    public SquaresDrawer( Environment env ){
        super(env);
    }

    public SquaresDrawer(){
    }

    @Override
    public void paint(Graphics g) {
        if( environment != null ){
            Squares reversi = (Squares)environment;
            if( reversi.board != null )
               reversi.board.draw(g, DRAW_AREA_SIZE, MARGIN);
            if( reversi.clock != null ){
                if( reversi.clock.white_turn()){
                   g.fillOval( 10, DRAW_AREA_SIZE+10, 10, 10);
                }else{
                   g.fillOval( 210, DRAW_AREA_SIZE+10, 10, 10);
                }
                g.drawString("Blue:"+reversi.clock.white_time_string(), 20, DRAW_AREA_SIZE+20);
                g.drawString("Red:"+reversi.clock.black_time_string(), 230, DRAW_AREA_SIZE+20);
            }
        }
    }

    @Override
    public void setDimension(int width, int height) {
        DRAW_AREA_SIZE = Math.min(width, height-30);
        MARGIN = DRAW_AREA_SIZE/20;
    }
}    