/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.labyrinth.multeseo;

import java.awt.Graphics;
import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.simulate.util.SimpleLanguage;

/**
 *
 * @author Jonatan
 */
public class MultiAgentLabyrinthPercept extends LabyrinthPercept{
  public MultiAgentLabyrinthPercept( int value, SimpleLanguage language ) {
    super( value, language);
    int flag = (1<<4);
    setAttribute( language.getPercept(4), (value & flag)==flag );
      
  }    
 
  @Override
  public void rotate( SimpleLanguage language ){
    super.rotate(language);
    Object f = getAttribute(language.getPercept(5));
    for( int i=5; i<8; i++ ){
      setAttribute( language.getPercept(i), getAttribute(language.getPercept(i+1)));
    }
    setAttribute( language.getPercept(8), f );
  }

  public void draw( Graphics g, int x, int y, int CELL_SIZE, SimpleLanguage language ){
    super.draw(g,x,y,CELL_SIZE,language);
    if( ((Boolean)getAttribute(language.getPercept(4))).booleanValue() ){
      g.drawLine(x,y,x+CELL_SIZE,y+CELL_SIZE);
      g.drawLine(x,y+CELL_SIZE,x+CELL_SIZE,y);
    }
  }
  
}
