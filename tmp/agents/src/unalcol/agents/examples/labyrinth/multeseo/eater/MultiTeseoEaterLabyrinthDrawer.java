package unalcol.agents.examples.labyrinth.multeseo.eater;

import java.awt.Color;
import java.awt.Graphics;

import unalcol.agents.examples.labyrinth.Labyrinth;
import unalcol.agents.examples.labyrinth.LabyrinthDrawer;
import unalcol.agents.examples.labyrinth.LabyrinthPerceptDrawer;
import unalcol.agents.examples.labyrinth.teseoeater.TeseoEaterLabyrinth;
import unalcol.agents.examples.labyrinth.teseoeater.TeseoEaterPerceptDrawer;
import unalcol.agents.simulate.Environment;
import unalcol.agents.simulate.SimulatedAgent;

public class MultiTeseoEaterLabyrinthDrawer extends LabyrinthDrawer{
    public Color[] energy_colors = new Color[]{
	      							Color.red, Color.orange, Color.yellow, Color.blue, Color.green, Color.green
	      						};
    public MultiTeseoEaterLabyrinthDrawer( Environment _environment, LabyrinthPerceptDrawer pDrawer) {
	super( _environment , pDrawer);      
    }

	  /**
	   * Default constructor
	   */
	  public MultiTeseoEaterLabyrinthDrawer(LabyrinthPerceptDrawer pDrawer) {
	      this.pDrawer = pDrawer;
	 }

	  /**
	   * Default constructor
	   */
	  public MultiTeseoEaterLabyrinthDrawer() {
	      this( new TeseoEaterPerceptDrawer() );
	 }
    /**
     * Paints the graphic component
     * @param g Graphic component
     */
    public void paint(Graphics g){
	super.paint(g);
      if( environment != null && ((Labyrinth)environment).structure != null ){
        Labyrinth   env = (Labyrinth)environment;
        int[] energy_level = ( (MultiTeseoEaterLabyrinth) environment).agent_energy_level;
        for( int i=0; i<env.agentsNumber(); i++){
            Color e_color = energy_colors[energy_level[i] * (energy_colors.length - 1) /
            TeseoEaterLabyrinth.MAX_ENERGY_LEVEL];
            g.setColor(e_color);
            SimulatedAgent agent = (SimulatedAgent)this.environment.getAgent(i);
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
}
