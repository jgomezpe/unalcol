/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

import unalcol.agents.Agent;

/**
 *
 * @author Jonatan
 */
public class ReversiMain {
  public static void main( String[] argv ){
    // Reflection
    Agent w_agent = new Agent( new DummyReversiAgentProgram("white") );
    Agent b_agent = new Agent( new DummyReversiAgentProgram("black") );
    ReversiMainFrame frame = new ReversiMainFrame( w_agent, b_agent );
    frame.setVisible(true);
  }
    
}
