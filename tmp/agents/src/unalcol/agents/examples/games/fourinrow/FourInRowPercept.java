/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.games.fourinrow;

import unalcol.agents.Percept;
import unalcol.agents.examples.games.Clock;

/**
 *
 * @author Jonatan
 */
public class FourInRowPercept extends Percept{
    
    protected Board board;
    protected Clock clock;
  
    public FourInRowPercept( Board board, Clock clock ) {
      this.board = board;
      this.clock = clock;
    }
    
    @Override
    public Object getAttribute( String code ){
        if( code.equals(FourInRow.TURN) ){
            if( clock.white_turn() ){
                return FourInRow.WHITE;
            }else{
                return FourInRow.BLACK;
            }
        }else{
            if( code.equals(FourInRow.WHITE + "_" + FourInRow.TIME) ){
                    return clock.white_time_string();
            }else{
                if( code.equals(FourInRow.BLACK + "_" + FourInRow.TIME) ){
                        return clock.white_time_string();
                }else{
                    if( code.equals(FourInRow.SIZE ) ){
                        return ""+board.values.length;
                    }else{
                        String[] v = code.split(":");
                        int i = Integer.parseInt(v[0]);
                        int j = Integer.parseInt(v[1]);
                        switch( board.values[i][j]){
                            case -1:
                                return FourInRow.BLACK;
                            case 1:
                                return FourInRow.WHITE;
                            default:
                                return FourInRow.SPACE;
                        }
                    }
                }    
            }            
        }
    }
}
