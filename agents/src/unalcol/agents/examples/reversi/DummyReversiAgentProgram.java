/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;

/**
 *
 * @author Jonatan
 */
public class DummyReversiAgentProgram implements AgentProgram {
    protected String color;
    public DummyReversiAgentProgram( String color ){
        this.color = color;        
    }
    
    @Override
    public Action compute(Percept p) {        
        long time = (long)(200 * Math.random());
        try{
           Thread.sleep(time);
        }catch(Exception e){}
        if( p.getAttribute(Reversi.TURN).equals(color) ){
            int i = (int)(8*Math.random());
            int j = (int)(8*Math.random());
            return new Action( i+":"+j+":"+color );
        }
        System.out.println("Stealing turn");
        return new Action(Reversi.PASS);
    }

    @Override
    public void init() {
    }
    
}
