package unalcol.agents.examples.sudoku.naive;
import unalcol.agents.search.*;
import unalcol.reflect.tag.TaggedObject;

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
public class NaiveSudokuGoalTest implements GoalTest<NaiveSudokuBoardState>{
  public NaiveSudokuGoalTest() {
  }
  
@Override
public Boolean apply(TaggedObject<NaiveSudokuBoardState> arg0) {
    // TODO Auto-generated method stub
    return null;
}

@Override
public Boolean apply(NaiveSudokuBoardState state) {
    return state.board.solved();
}
}
