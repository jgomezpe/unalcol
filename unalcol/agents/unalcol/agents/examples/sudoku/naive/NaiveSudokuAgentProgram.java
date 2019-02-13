package unalcol.agents.examples.sudoku.naive;
import unalcol.agents.*;
import unalcol.agents.examples.sudoku.*;
import unalcol.agents.search.classic.*;
import unalcol.agents.search.util.*;
import unalcol.collection.Vector;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public class NaiveSudokuAgentProgram implements AgentProgram{
  Vector<Action> cmd = new Vector<Action>();
  public NaiveSudokuAgentProgram() {
  }

  public void init(){
    cmd.clear();
  }

  public Action compute( Percept percept ){
    if( cmd.size() == 0 ){
      NaiveSudokuBoardState initial_state = new NaiveSudokuBoardState((SudokuPercept)percept);
      int depth = initial_state.board.emptyPlaces();
      DepthFirstSearch<NaiveSudokuBoardState> search = new DepthFirstSearch<NaiveSudokuBoardState>(depth);
      cmd = search.apply( initial_state, new NaiveSudokuSearchSpace(),
                          new NaiveSudokuGoalTest(), new ConstantCost<NaiveSudokuBoardState>() );
      if( cmd == null ){ cmd = new Vector<Action>(); }
    }
    if( cmd.size() > 0 ){
      try{
    	Action action = cmd.get(0);
    	cmd.remove(0);
    	return action;
      }catch(Exception e){}
    }
    return null;
  }
}
