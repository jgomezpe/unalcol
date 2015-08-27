/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.squares;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.Percept;
import unalcol.agents.examples.reversi.Clock;
import unalcol.agents.simulate.Environment;
import unalcol.agents.simulate.gui.EnvironmentView;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Jonatan
 */
public class Squares extends Environment{
    public static String PASS = "PASS";
    public static String TURN = "play";
    public static String TIME = "time";
    public static String WHITE = "white";
    public static String BLACK = "black";
    public static String SPACE = "space";
    public static String LEFT = "left";
    public static String TOP = "top";
    public static String RIGHT = "right";
    public static String BOTTOM = "bottom";
    public static String SIZE = "size";
    public static String COLOR = "color";
    public static String TRUE = "true";
    public static String FALSE = "false";
    
    protected Board board = null;
    protected Clock clock;
    
    protected static Vector<Agent> init( Agent white, Agent black ){
        Vector<Agent> a = new Vector<Agent>();
        a.add(white);        
        a.add(black);
        return a;
    }
    
    public Squares( Agent white, Agent black ){
        super( init( white, black ) );
    }
    

    public void init(Board b, Clock c){
        clock = c;
        board = b;
    }

    @Override
    public Percept sense(Agent agent) {
        return new SquaresPercept(board, clock);
    }

    @Override
    public boolean act(Agent agent, Action action){
        if(board.full()){
            agents.get(0).die();
            agents.get(1).die();            
            int w = board.white_count();
            int b = board.black_count();
            if( w > b ){
               updateViews(EnvironmentView.DONE + ": Blue wins");
            }else{
               if( b > w ){
                  updateViews(EnvironmentView.DONE + ": Red wins");
               }else{
                  updateViews(EnvironmentView.DONE + ": Draw");
               }
            }
        }

        if(clock.white_turn()){
            if( agent != agents.get(0)){
                updateViews("Working");
                return false;                
            }
            if(clock.white_time() <= 0 ){
                agents.get(0).die();
                agents.get(1).die();            
                updateViews(EnvironmentView.DONE + ": Red wins");
            }
        }else{
            if( agent != agents.get(1)){
                updateViews("Working");
                return false;                
            }
            if(clock.black_time() <= 0 ){
                agents.get(0).die();
                agents.get(1).die();            
                updateViews(EnvironmentView.DONE + ": Blue wins");
            }
        }
        String[] code = action.getCode().split(":");
        int i = Integer.parseInt(code[0]);
        int j = Integer.parseInt(code[1]);
        int side = 0;
        if( code[2].equals(LEFT)) side = Board.LEFT; 
        if( code[2].equals(TOP)) side = Board.TOP; 
        if( code[2].equals(RIGHT)) side = Board.RIGHT; 
        if( code[2].equals(BOTTOM)) side = Board.BOTTOM; 
        if( board.play(clock.white_turn(), i, j, side)){
          clock.swap();
          updateViews("Working");
          return true;
        }
        //clock.swap();
        updateViews("Working");
        return false;
    }

    @Override
    public void init(Agent agent) {
    }

    @Override
    public Vector<Action> actions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
