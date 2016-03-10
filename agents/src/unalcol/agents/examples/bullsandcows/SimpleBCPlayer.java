/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.bullsandcows;
import unalcol.types.collection.vector.Vector;
import unalcol.random.raw.JavaGenerator;
/**
 *
 * @author Jonatan
 */
public class SimpleBCPlayer {
    protected NumberIndex ni;
    protected Vector<Integer> options = new Vector<Integer>();
    public SimpleBCPlayer( NumberIndex _ni ){
        ni = _ni;
        int s = ni.size();
        for( int i=0; i<s; i++ ){
            options.add(i);
        }
    }
    
    public int[] next(){
        JavaGenerator g = new JavaGenerator();
    	
        return ni.getOption( options.get(g.integer(options.size())) );        
    }
    
    public boolean check( int[] option, int[] bc ){
        int i=0;
        while(  i<options.size() ){
            int[] opt = ni.getOption(options.get(i));            
            int[] bc2 = NumberIndex.compare(option, opt);
            if( bc2[0] != bc[0] || bc2[1] != bc[1] ){
                options.remove(i);
            }else{
                i++;
            }
        }
        return options.size()>0;
    }
}
