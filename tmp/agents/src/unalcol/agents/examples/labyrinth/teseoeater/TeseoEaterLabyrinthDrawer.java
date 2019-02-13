package unalcol.agents.examples.labyrinth.teseoeater;

import unalcol.agents.simulate.*;

import java.awt.*;
import unalcol.agents.examples.labyrinth.*;

/**
 * Panel to draw a fuzzy space, and highlight a specific set and value
 */
public class TeseoEaterLabyrinthDrawer extends LabyrinthDrawer{
  public Color[] energy_colors = new Color[]{
      Color.red, Color.orange, Color.yellow, Color.blue, Color.green, Color.green
  };



  /**
   * Default constructor
   */
  public TeseoEaterLabyrinthDrawer( Environment _environment, LabyrinthPerceptDrawer pDrawer ) {
      super( _environment , pDrawer );
  }

  /**
   * Default constructor
   */
  public TeseoEaterLabyrinthDrawer( Environment _environment ) {
      super( _environment , new TeseoEaterPerceptDrawer() );
  }

  /**
   * Default constructor
   */
  public TeseoEaterLabyrinthDrawer(LabyrinthPerceptDrawer pDrawer) {
      super( pDrawer);
  }

  /**
   * Default constructor
   */
  public TeseoEaterLabyrinthDrawer() {
      super( new TeseoEaterPerceptDrawer() );
  }

  
  protected int getCanvasValue( int val ){
    return val*CELL_SIZE+MARGIN;
  }


  /**
   * Paints the graphic component
   * @param g Graphic component
   */
  public void paint(Graphics g){
    super.paint(g);
    if( environment != null ){
      int energy_level = ( (TeseoEaterLabyrinth) environment).energy_level;
      Color e_color = energy_colors[energy_level * (energy_colors.length - 1) /
          TeseoEaterLabyrinth.MAX_ENERGY_LEVEL];
      g.setColor(e_color);
      SimulatedAgent agent = (SimulatedAgent)this.environment.getAgent();
      int x = ( (Integer) agent.getAttribute(Labyrinth.X)).intValue();
      int y = ( (Integer) agent.getAttribute(Labyrinth.Y)).intValue();
      int X = getCanvasValue(x);
      int Y = getCanvasValue(y);
      int DELTA = CELL_SIZE / 8;
      g.fillOval(X + 2*DELTA, Y + 2*DELTA, CELL_SIZE - 4 * DELTA,
                 CELL_SIZE - 4 * DELTA);
    }
  }

}
