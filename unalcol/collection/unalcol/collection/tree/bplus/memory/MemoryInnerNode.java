/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.collection.tree.bplus.memory;
import unalcol.collection.tree.bplus.*;
import unalcol.collection.tree.bplus.immutable.ImmutableNode;

/**
 *
 * @author jgomez
 */
public class MemoryInnerNode<T> extends MemoryNode<T> implements BPlusInnerNode<T>{
    protected ImmutableNode<T>[] next;
    protected T leftKey;
    
    @SuppressWarnings("unchecked")
	public MemoryInnerNode( int SIZE ){
        next = new BPlusNode[SIZE];
        n = 0;
    }

    public MemoryInnerNode( ImmutableNode<T>[] next, int n ){
        this.next = next;
        this.n = n;
        for( int i=0; i<n; i++){
        	((BPlusNode<T>)next[i]).setParent(this);
        }
        //this.updateLeftKey();
    }

    @Override
    public ImmutableNode<T> newInstance(int SIZE){
        return new MemoryInnerNode<T>(SIZE);
    }
    
    @Override
    public BPlusInnerNode<T> newInstance( ImmutableNode<T>[] next, int n ){
       return new MemoryInnerNode<T>(next,n);
    }
    
    // Size
    @Override
    public int size(){
        return next.length;
    }
    
    @Override
    public ImmutableNode<T>[] next(){
        return next;
    }

    public void setNext( ImmutableNode<T>[] next ){
    	this.next = next;
    }
    
    public void setNext( ImmutableNode<T> node, int index ){
    	this.next[index] = node;
    }    
}