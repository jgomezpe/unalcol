/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection.tree.bplus.memory;

import unalcol.collection.tree.bplus.BPlusInnerNode;
import unalcol.collection.tree.bplus.BPlusNode;
import unalcol.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.collection.tree.bplus.immutable.ImmutableNode;

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
    public ImmutableNode<T> left(){
        return left;
    }

    @Override
    public void setLeft( ImmutableNode<T> node ){
        left = (BPlusNode<T>)node;
    }

    @Override
    public ImmutableNode<T> right(){
        return right;
    }
    
    @Override
    public void setRight( ImmutableNode<T> node ){
        right = (BPlusNode<T>)node;
    }

    //Parent
    @Override
    public ImmutableInnerNode<T> parent(){
        return parent;
    }

    @Override
    public void setParent( ImmutableInnerNode<T> node ){
        parent = (BPlusInnerNode<T>)node;
    }        
}