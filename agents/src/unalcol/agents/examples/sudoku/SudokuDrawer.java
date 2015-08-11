package unalcol.agents.examples.sudoku;

import unalcol.agents.simulate.*;
import unalcol.agents.simulate.gui.*;

import java.awt.*;


/**
 * Panel to draw a fuzzy space, and highlight a specific set and value
 */
public class SudokuDrawer extends Drawer{

  public static int DRAW_AREA_SIZE = 180;
  public static int CELL_SIZE = 20;
  public static int MARGIN = 10;
  public static int SHAPE_SIZE = 1;


  public Color[] colors = new Color[]{
      Color.black, Color.blue, Color.red, Color.yellow,
      Color.cyan, Color.green,
      Color.pink,  Color.orange, Color.magenta,
      Color.gray, Color.darkGray, Color.lightGray
  };

  /**
   * Default constructor
   */
  public SudokuDrawer( Environment _environment ) {
      super( _environment );
  }

  /**
   * Default constructor
   */
  public SudokuDrawer() {
  }

  protected int getCanvasValue( int val ){
    return val*CELL_SIZE+MARGIN;
  }

  public void setDimension( int width, int height ){
  }


  /**
   * Paints the graphic component
   * @param g Graphic component
   */
  public void paint(Graphics g){
      if (environment != null) {
          Sudoku env = (Sudoku) environment;
          Board board = env.getBoard();

          int n = SudokuLanguage.DIGITS;
          int sqrt_n = (int) (Math.sqrt(n) + 0.1);

          g.setColor(Color.lightGray);
          Font font = g.getFont();
          g.setFont( new Font(font.getName(), font.getStyle(), 20 ));
          for (int i = 0; i<n; i++) {
              int ci = getCanvasValue(i);
              if (i % sqrt_n == 0) {
                  g.drawLine(ci, DRAW_AREA_SIZE + MARGIN, ci, MARGIN);
                  g.drawLine(DRAW_AREA_SIZE + MARGIN, ci, MARGIN, ci);
              }

              for (int j = 0; j < n; j++) {
                  int cj = getCanvasValue(j);
                  int value = board.get(i, j);
                  if( value > 0 ){
                      g.setColor(Color.black);
                      g.drawString(""+value, cj+CELL_SIZE/5, ci+CELL_SIZE);
                      g.setColor(Color.lightGray);
                  }
              }

          }
          g.drawLine(DRAW_AREA_SIZE + MARGIN, DRAW_AREA_SIZE + MARGIN, DRAW_AREA_SIZE + MARGIN, MARGIN);
          g.drawLine(DRAW_AREA_SIZE + MARGIN, DRAW_AREA_SIZE + MARGIN, MARGIN, DRAW_AREA_SIZE + MARGIN);
      }
  }
}
