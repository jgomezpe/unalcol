package unalcol.agents.examples.labyrinth;
import unalcol.agents.Percept;

import java.awt.Graphics;
import unalcol.agents.simulate.util.SimpleLanguage;

public class LabyrinthPercept extends Percept{
  
  public LabyrinthPercept( int value, SimpleLanguage language ) {
    int n = 4;
    for( int i=0; i<n; i++ ){
      int flag = (1<<i);
      setAttribute( language.getPercept(i), (value & flag)==flag );
    }
  }
    
  public void draw( Graphics g, int x, int y, int CELL_SIZE, SimpleLanguage language ){
    if( ((Boolean)getAttribute(language.getPercept(0))).booleanValue() ){ g.drawLine(x,y,x+CELL_SIZE,y); }
    if( ((Boolean)getAttribute(language.getPercept(1))).booleanValue() ){ g.drawLine(x+CELL_SIZE,y,x+CELL_SIZE,y+CELL_SIZE); }
    if( ((Boolean)getAttribute(language.getPercept(2))).booleanValue() ){ g.drawLine(x,y+CELL_SIZE,x+CELL_SIZE,y+CELL_SIZE); }
    if( ((Boolean)getAttribute(language.getPercept(3))).booleanValue() ){ g.drawLine(x,y,x,y+CELL_SIZE); }
  }

  public void rotate(SimpleLanguage language){
    Object f = getAttribute(language.getPercept(0));
    for( int i=0; i<3; i++ ){
      setAttribute( language.getPercept(i), getAttribute(language.getPercept(i+1)));
    }
    setAttribute( language.getPercept(3), f );
  }
}
