/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.labyrinth.multeseo;

import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.examples.labyrinth.LabyrinthUtil;

/**
 *
 * @author Jonatan
 */
public class MultiAgentLabyrinthPercept extends LabyrinthPercept{
  public MultiAgentLabyrinthPercept( int value ) {
    super( value );
    int flag = (1<<4);
    setAttribute( LabyrinthUtil.TREASURE, (value & flag)==flag );     
  }    
 
  @Override
  public void rotate( ){
    super.rotate();
    Object f = getAttribute(LabyrinthUtil.AGENT[0]);
    for( int i=0; i<3; i++ ){
      setAttribute( LabyrinthUtil.AGENT[i], getAttribute(LabyrinthUtil.AGENT[i+1]) );
    }
    setAttribute( LabyrinthUtil.AGENT[3], f );
  }
}
