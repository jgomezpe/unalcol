/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

/**
 *
 * @author jgomez
 */
public class LabeledObject<T> {
    protected int label;
    protected T obj;
    public LabeledObject( int label, T obj ){
        this.label = label;
        this.obj = obj;
    }
    
    public int label(){
        return label;
    }
    
    public T object(){
        return obj;
    }
    
    public void setLabel( int label ){
        this.label = label;
    }
}
