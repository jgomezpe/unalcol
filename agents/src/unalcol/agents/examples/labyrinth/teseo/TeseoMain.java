package unalcol.agents.examples.labyrinth.teseo;
import unalcol.agents.Agent;

import unalcol.agents.examples.labyrinth.*;
import unalcol.agents.examples.labyrinth.teseo.simple.RandomReflexTeseo;
import unalcol.agents.simulate.util.*;

public class TeseoMain {
  private static SimpleLanguage getLanguage(){
    return  new SimpleLanguage( new String[]{"front", "right", "back", "left", "treasure", "fail",
        "afront", "aright", "aback", "aleft"},
                                   new String[]{"no_op", "die", "advance", "rotate"}
                                   );
  }

  public static void main( String[] argv ){
    //  InteractiveAgentProgram p = new InteractiveAgentProgram( getLanguage() );
//    TeseoSimple p = new TeseoSimple();
    RandomReflexTeseo p = new RandomReflexTeseo();
    p.setLanguage(getLanguage());
    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
    LabyrinthDrawer.CELL_SIZE = 40;
    Labyrinth.DEFAULT_SIZE = 15;
    Agent agent = new Agent( p );
    TeseoMainFrame frame = new TeseoMainFrame( agent, getLanguage() );
    frame.setVisible(true); 
  }
}
