/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.squares;

import unalcol.agents.Percept;
import unalcol.agents.examples.reversi.Clock;

/**
 *
 * @author Jonatan
 */
public class SquaresPercept extends Percept{
    
    protected Board board;
    protected Clock clock;
  
    public SquaresPercept( Board board, Clock clock ) {
      this.board = board;
      this.clock = clock;
    }
    
    @Override
    public Object getAttribute( String code ){
      if( code.equals(Squares.TURN) ){
        if( clock.white_turn() ){
          return Squares.WHITE;
        }else{
         return Squares.BLACK;
        }
      }else{
        if( code.equals(Squares.WHITE + "_" + Squares.TIME) )  return clock.white_time_string();
        if( code.equals(Squares.BLACK + "_" + Squares.TIME) )  return clock.black_time_string();
        if( code.equals(Squares.SIZE ) )  return ""+board.values.length;
        String[] v = code.split(":");
        int i = Integer.parseInt(v[0]);
        int j = Integer.parseInt(v[1]);
        if(v[2].equals(Squares.LEFT))
            if((board.values[i][j]&Board.LEFT)==Board.LEFT) return Squares.TRUE;
            else return Squares.FALSE;
        if(v[2].equals(Squares.TOP))
            if((board.values[i][j]&Board.TOP)==Board.TOP) return Squares.TRUE;
            else return(Squares.FALSE); 
        if(v[2].equals(Squares.RIGHT))
            if((board.values[i][j]&Board.RIGHT)==Board.RIGHT) return Squares.TRUE;
            else return(Squares.FALSE); 
        if(v[2].equals(Squares.BOTTOM))
            if((board.values[i][j]&Board.BOTTOM)==Board.BOTTOM) return Squares.TRUE;
            else return(Squares.FALSE); 
        if( board.lines(i,j)==4 ){
          if((board.values[i][j]&Board.WHITE)==Board.WHITE)  return Squares.WHITE;
          return Squares.BLACK;
        }else{  
          return Squares.SPACE;
        }
      }
    }     
}
