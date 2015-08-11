package unalcol.agents.examples.labyrinth.teseo;
import unalcol.agents.examples.labyrinth.*;

import java.awt.Graphics;
import unalcol.agents.simulate.util.*;

public class TeseoPercept extends LabyrinthPercept{
  public TeseoPercept( int value, SimpleLanguage language ) {
    super( value, language );
    int flag = (1<<4);
    setAttribute( language.getPercept(4), (value & flag)==flag );
  }

  @Override
  public void draw( Graphics g, int x, int y, int CELL_SIZE, SimpleLanguage language ){
    super.draw(g,x,y,CELL_SIZE, language);
    if( ((Boolean)getAttribute(language.getPercept(4))).booleanValue() ){
      g.drawLine(x,y,x+CELL_SIZE,y+CELL_SIZE);
      g.drawLine(x,y+CELL_SIZE,x+CELL_SIZE,y);
    }
  }
}
