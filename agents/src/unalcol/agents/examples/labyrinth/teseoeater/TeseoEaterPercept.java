package unalcol.agents.examples.labyrinth.teseoeater;
import unalcol.agents.examples.labyrinth.*;
import java.awt.*;
import unalcol.agents.simulate.util.*;

public class TeseoEaterPercept extends LabyrinthPercept{

  public static String TREASURE = "treasure";
  public static String ENERGY_LEVEL = "energy_level";
  public static String[] RESOURCE = new String[]{"resource", "resource-color", "resource-shape", "resource-size", "resource-weight", "resource-type"};
  public Color[] colors = new Color[]{
      Color.black, Color.blue
  };

  public TeseoEaterPercept( int value, int energy_level, SimpleLanguage language ){
    super( value, language );
    int flag = (1<<4);
    setAttribute( language.getPercept(4), (value & flag)==flag );
    setFood( value, language );
    setAttribute( language.getPercept(10), energy_level );
  }

  protected void setFood( int value, SimpleLanguage language ){
    int flag = (1<<5);
    boolean thereis = (value & flag)==flag;
    setAttribute(language.getPercept(5), thereis);
    if( thereis ){
      for( int bit=6; bit<10; bit++ ){
          flag = (1 << bit);
          setAttribute(language.getPercept(bit), (value & flag)==flag);
      }
      flag = (1<<15);
      if( (value & flag) == flag ){
        flag = (1 << 10);
        setAttribute(language.getPercept(10), (value & flag)==flag);
      }else{
        setAttribute(language.getPercept(10), false);
      }
    }
  }


  public void draw( Graphics g, int x, int y, int CELL_SIZE, SimpleLanguage language ){
    super.draw(g, x, y, CELL_SIZE, language );
    if( ((Boolean)getAttribute(language.getPercept(4))).booleanValue() ){ // treasure
      g.drawLine(x,y,x+CELL_SIZE,y+CELL_SIZE);
      g.drawLine(x,y+CELL_SIZE,x+CELL_SIZE,y);
    }

    drawFood( g, x, y, CELL_SIZE, language );
  }

  protected void drawFood( Graphics g, int x, int y, int CELL_SIZE, SimpleLanguage language ){
    int index = 5;
    if( ((Boolean)getAttribute(language.getPercept(index))).booleanValue() ){ // key or lock
        index++;
      int DELTA = CELL_SIZE / 8;
      int dx = DELTA;
      for( int k=0; k<4; k++ ){
          if( ((Boolean)getAttribute(language.getPercept(index))).booleanValue() ){ // key or lock
              g.drawLine(x + dx, y + 2, x + dx, y + 2*DELTA);
          }
          index++;
          dx += DELTA;
      }

      dx += DELTA;
/*      for( int k=0; k<3; k++ ){
          if( ((Boolean)getAttribute(language.getPercept(index))).booleanValue() ){ // key or lock
              g.drawLine(x + dx, y + 2, x + dx, y + 2*DELTA);
          }
          index++;
          dx += DELTA;
      } */
    }
  }
}
