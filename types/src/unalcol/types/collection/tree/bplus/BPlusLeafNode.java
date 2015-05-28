/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.tree.bplus;

/**
 *
 * @author jgomez
 */
public interface BPlusLeafNode<T> extends BPlusNode<T>{
    public BPlusLeafNode<T> newInstance( T[] keys, int n );        
    
    //key's methods
    public T[] keys();    
    public T key( int index );
    public boolean insert( int pos, T key );    
    public boolean remove( int pos );    
    public boolean append( T key );        
    public void set( int i, T key );    
}