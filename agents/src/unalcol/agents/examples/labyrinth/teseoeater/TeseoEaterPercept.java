package unalcol.agents.examples.labyrinth.teseoeater;
import unalcol.agents.examples.labyrinth.*;
import unalcol.agents.examples.labyrinth.teseo.TeseoPercept;

import java.awt.*;

public class TeseoEaterPercept extends TeseoPercept{
  public Color[] colors = new Color[]{
      Color.black, Color.blue
  };

  public TeseoEaterPercept( int value ){
    super( value );
    setFood( value );
  }

  protected void setFood( int value ){
      int FOOD_POS = 5;
      int FOOD_PROPERTIES = LabyrinthUtil.RESOURCE.length-1;
      int flag = (1<<FOOD_POS);
      boolean thereis = (value & flag)==flag;
      setAttribute(LabyrinthUtil.RESOURCE[0], thereis);
      if( thereis ){
	  for( int bit=1; bit<=FOOD_PROPERTIES; bit++ ){
	      flag = (1 << (bit+FOOD_POS));
	      setAttribute(LabyrinthUtil.RESOURCE[bit], (value & flag)==flag);
	  }
/*
	  flag = (1<<15);
	  if( (value & flag) == flag ){
	      flag = (1 << 10);
	      setAttribute(language.getPercept(10), (value & flag)==flag);
	  }else{
	      setAttribute(language.getPercept(10), false);
	  }
*/	  
      }
  }
}
