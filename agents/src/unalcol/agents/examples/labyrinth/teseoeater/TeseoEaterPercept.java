package unalcol.agents.examples.labyrinth.teseoeater;
import unalcol.agents.examples.labyrinth.*;
import java.awt.*;
import unalcol.agents.simulate.util.*;

public class TeseoEaterPercept extends LabyrinthPercept{
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
}
