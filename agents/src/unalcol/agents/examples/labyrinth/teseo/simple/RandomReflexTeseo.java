/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.labyrinth.teseo.simple;

/**
 *
 * @author Jonatan
 */
public class RandomReflexTeseo  extends SimpleTeseoAgentProgram {

    public RandomReflexTeseo() {}
    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {
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
