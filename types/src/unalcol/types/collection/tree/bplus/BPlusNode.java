/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus;

/**
 *
 * @author jgomez
 */
public interface BPlusNode<T> {
    public BPlusNode<T> newInstance(int SIZE);
    
    //key's methods
    // Left key
    public T leftKey();  
    public T updateLeftKey();

    // Size analysis
    public int n();
    public void setn( int n );            
    public int size();        
    public int underFillSize();
    public boolean isFull();
    public boolean underFill();
    
    // Siblings    
    public BPlusNode<T> left();
    public void         setLeft( BPlusNode<T> node );
    public BPlusNode<T> right();
    public void         setRight( BPlusNode<T> node );
    
    // Balance
    public void merge();
    public void leftShift();
    public void rightShift();
    public void split();
    
    // Parent
    public BPlusInnerNode<T> parent();    
    public void setParent( BPlusInnerNode<T> node );
    
}
