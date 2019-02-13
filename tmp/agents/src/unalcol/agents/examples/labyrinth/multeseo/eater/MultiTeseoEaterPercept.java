package unalcol.agents.examples.labyrinth.multeseo.eater;

import unalcol.agents.examples.labyrinth.LabyrinthUtil;
import unalcol.agents.examples.labyrinth.teseoeater.TeseoEaterPercept;

public class MultiTeseoEaterPercept extends TeseoEaterPercept {

    public MultiTeseoEaterPercept(int value) {
	super(value);	
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