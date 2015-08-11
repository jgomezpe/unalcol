/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

import unalcol.agents.Percept;

/**
 *
 * @author Jonatan
 */
public class ReversiPercept extends Percept{
    
    protected Board board;
    protected Clock clock;
  
    public ReversiPercept( Board board, Clock clock ) {
      this.board = board;
      this.clock = clock;
    }
    
    @Override
    public Object getAttribute( String code ){
        if( code.equals(Reversi.TURN) ){
            if( clock.white_turn() ){
                return Reversi.WHITE;
            }else{
                return Reversi.BLACK;
            }
        }else{
            if( code.equals(Reversi.WHITE + "_" + Reversi.TIME) ){
                    return clock.white_time_string();
            }else{
                if( code.equals(Reversi.BLACK + "_" + Reversi.TIME) ){
                        return clock.white_time_string();
                }else{
                    if( code.equals(Reversi.SIZE ) ){
                        return ""+board.values.length;
                    }else{
                        String[] v = code.split(":");
                        int i = Integer.parseInt(v[0]);
                        int j = Integer.parseInt(v[1]);
                        switch( board.values[i][j]){
                            case -1:
                                return Reversi.BLACK;
                            case 1:
                                return Reversi.WHITE;
                            default:
                                return Reversi.SPACE;
                        }
                    }
                }    
            }            
        }
    }
}
