package unalcol.agents.examples.sudoku;
import unalcol.agents.*;

import unalcol.agents.simulate.util.*;
import unalcol.services.Service;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.collection.vector.Vector;
import unalcol.agents.examples.sudoku.naive.*;

public class SudokuMain {
	public static void init_services(){
		Service.register(new VectorClone<>(), Vector.class);      
	}
	
  private static Language getLanguage(){
    return  new SudokuLanguage();
  }

  public static void main( String[] argv ){
	  init_services();
    //    Agent agent = new Agent( new InteractiveAgentProgram( getLanguage() ) );
    Agent agent = new Agent( new NaiveSudokuAgentProgram() );
    SudokuMainFrame frame = new SudokuMainFrame( agent, getLanguage() );
    frame.setVisible(true);
  }
}
