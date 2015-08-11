/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.squares;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Jonatan
 */
public class DummySquaresAgentProgram implements AgentProgram {
    protected String color;
    public DummySquaresAgentProgram( String color ){
        this.color = color;        
    }
    
    @Override
    public Action compute(Percept p) {
        long time = (long)(200 * Math.random());
        try{
           Thread.sleep(time);
        }catch(Exception e){}
        if( p.getAttribute(Squares.TURN).equals(color) ){
            int size = Integer.parseInt((String)p.getAttribute(Squares.SIZE));
            int i = 0;
            int j = 0;
            Vector<String> v = new Vector<String>();
            while(v.size()==0){
              i = (int)(size*Math.random());
              j = (int)(size*Math.random());
              if(((String)p.getAttribute(i+":"+j+":"+Squares.LEFT)).equals(Squares.FALSE))
                v.add(Squares.LEFT);
              if(((String)p.getAttribute(i+":"+j+":"+Squares.TOP)).equals(Squares.FALSE))
                v.add(Squares.TOP);
              if(((String)p.getAttribute(i+":"+j+":"+Squares.BOTTOM)).equals(Squares.FALSE))
                v.add(Squares.BOTTOM);
              if(((String)p.getAttribute(i+":"+j+":"+Squares.RIGHT)).equals(Squares.FALSE))
                v.add(Squares.RIGHT);
            }
            return new Action( i+":"+j+":"+v.get((int)(Math.random()*v.size())) );
        }
        return new Action(Squares.PASS);
    }

    @Override
    public void init() {
    }
    
}

