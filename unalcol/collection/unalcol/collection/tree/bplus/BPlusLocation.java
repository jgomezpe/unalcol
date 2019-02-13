/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection.tree.bplus;

import unalcol.collection.tree.bplus.immutable.ImmutableLeafNode;

/**
 *
 * @author jgomez
 */
public class BPlusLocation<T>{
    protected int pos = -1;
    protected ImmutableLeafNode<T> node;
    
    public BPlusLocation(int pos, ImmutableLeafNode<T> node){
        this.pos = pos;
        this.node = node;
    } 
    
    public int pos(){ return pos; }
    
    public ImmutableLeafNode<T> node(){ return node; }
    
    public T get(){ return node.key(pos); }
}