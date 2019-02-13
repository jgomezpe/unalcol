package unalcol.agents.examples.sudoku;

import unalcol.agents.*;
import unalcol.agents.simulate.util.*;
import unalcol.agents.simulate.gui.*;
import unalcol.agents.simulate.*;
import unalcol.collection.Vector;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Sudoku extends Environment{
    protected Board board;
    protected int emptyPlaces;
    protected Language language;
    public Sudoku( Agent agent, Language _language, int _emptyPlaces ) {
        super( agent );
        language = _language;
        emptyPlaces = _emptyPlaces;
        init();
    }

    public void init( Agent agent ){
      //SimulatedAgent sim_agent = (SimulatedAgent)agent;
      //@TODO: Any special initialization processs of the environment
      init();
    }

    public void init(){
        board = new Board( emptyPlaces );
    }

    public void init( int _emptyPlaces ){
        emptyPlaces = _emptyPlaces;
        init();
    }

    protected Board getBoard(){ return board; }

    public Percept sense(Agent anAgent){
        return new SudokuPercept( board, (SudokuLanguage)language );
    }

    public boolean act(Agent agent, Action action){
      boolean flag = (action != null);
      if( flag  ){
          String act = action.getCode();
          int DIGITS = SudokuLanguage.DIGITS;
          int index = language.getActionIndex(act);
          if( index < language.getActionsNumber() - 1 ){
              int row = index / (DIGITS * DIGITS);
              index -= row * DIGITS * DIGITS;
              int column = index / DIGITS;
              index -= column * DIGITS;
              int value = index + 1;
              if (board.set(row, column, value)) {
                  if (board.solved()) {
                      getAgent().die();
                      updateViews(SimpleView.DONE);
                  } else {
                      updateViews("");
                  }
              } else {
                  updateViews(SimpleView.ERROR + "[Invalid action " + act + "]");
              }
          }
      }
      return flag;
    }

    public Vector<Action> actions(){
      Vector<Action> acts = new Vector<Action>();
      int n = language.getActionsNumber();
      for( int i=0; i<n; i++ ){
        acts.add( new Action( language.getAction(i) ) );
      }
      return acts;
    }


}
