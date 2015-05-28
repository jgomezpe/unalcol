/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus.memory;

import unalcol.types.collection.tree.bplus.BPlusNode;
import unalcol.types.collection.tree.bplus.BPlusInnerNode;

/**
 *
 * @author jgomez
 */
public abstract class MemoryNode<T> implements BPlusNode<T> {
    protected int n;
    protected BPlusNode<T> left = null;
    protected BPlusNode<T> right = null;
    protected BPlusInnerNode<T> parent = null;    
    
    // Size    
    @Override
    public int n(){ return n; }

    @Override
    public void setn( int n ){
        this.n = n;
    }
    
    //Siblings
    @Override
    public BPlusNode<T> left(){
        return left;
    }

    @Override
    public void setLeft( BPlusNode<T> node ){
        left = node;
    }

    @Override
    public BPlusNode<T> right(){
        return right;
    }
    
    @Override
    public void setRight( BPlusNode<T> node ){
        right = node;
    }

    //Parent
    @Override
    public BPlusInnerNode<T> parent(){
        return parent;
    }

    @Override
    public void setParent( BPlusInnerNode<T> node ){
        parent = node;
    }        
}
