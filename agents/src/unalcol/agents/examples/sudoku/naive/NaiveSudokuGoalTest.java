package unalcol.agents.examples.sudoku.naive;
import unalcol.agents.search.*;
import unalcol.algorithm.Algorithm;
import unalcol.sort.Order;

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
public class NaiveSudokuGoalTest extends Algorithm<NaiveSudokuBoardState,Boolean> implements GoalTest<NaiveSudokuBoardState>{
	public NaiveSudokuGoalTest() {}
  
	@Override
	public Boolean apply(NaiveSudokuBoardState state) {
		return state.board.solved();
	}

	@Override
	public Order<Boolean> order() {
		// TODO Auto-generated method stub
		return null;
	}
}