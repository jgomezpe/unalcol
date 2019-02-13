/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

/**
 *
 * @author jgomez
 */
public class InputOutputPair<S,T> {
    protected S input;
    protected T output;
    public InputOutputPair( S input, T output ){
        this.output = output;
        this.input = input;
    }
    
    public T output(){
        return output;
    }
    
    public S input(){
        return input;
    }
    
    public void setOutput( T output ){
        this.output = output;
    }
}
