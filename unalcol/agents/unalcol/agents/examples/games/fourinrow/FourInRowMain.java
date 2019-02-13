/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.games.fourinrow;

import unalcol.agents.Agent;

/**
 *
 * @author Jonatan
 */
public class FourInRowMain {
  public static void main( String[] argv ){
    // Reflection
    Agent w_agent = new Agent( new DummyFourInRowAgentProgram("white") );
    Agent b_agent = new Agent( new DummyFourInRowAgentProgram("black") );
    FourInRowMainFrame frame = new FourInRowMainFrame( w_agent, b_agent );
    frame.setVisible(true);
  }
    
}
