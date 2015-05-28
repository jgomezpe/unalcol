/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.tree.bplus;

/**
 *
 * @author jgomez
 */
public interface BPlusInnerNode<T> extends BPlusNode<T> {
    public BPlusInnerNode<T> newInstance( BPlusNode<T>[] nodes, int n );        
    // Next methods
    public BPlusNode<T>[]   next();    
    public BPlusNode<T>     next(int i);
    public boolean          append(BPlusNode<T> key );    
    public boolean          insert( int pos, BPlusNode<T> key );    
    public boolean          remove( int pos );    
    public void             set( int i, BPlusNode<T> key );    
    public BPlusLeafNode<T> mostLeft();
}