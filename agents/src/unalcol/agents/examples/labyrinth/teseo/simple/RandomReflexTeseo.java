/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.labyrinth.teseo.simple;

import unalcol.agents.simulate.util.SimpleLanguage;

/**
 *
 * @author Jonatan
 */
public class RandomReflexTeseo  extends SimpleTeseoAgentProgram {

    public RandomReflexTeseo() {}
    
    public RandomReflexTeseo(   SimpleLanguage _language  ) {
  	  super(_language);
    }

    
    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT, boolean FAIL) {
        if (MT) return -1;
        boolean flag = true;
        int k=0;
        while( flag ){
            k = (int)(Math.random()*4);
            switch(k){
                case 0:
                    flag = PF;
                    break;
                case 1:
                    flag = PD;
                    break;
                case 2:
                    flag = PA;
                    break;
                default:
                    flag = PI;
                    break;                    
            }
        }
        return k;
    }    
}
