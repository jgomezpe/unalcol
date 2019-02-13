/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection.tree.bplus;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.collection.tree.bplus.immutable.ImmutableLeafNode;

/**
 *
 * @author jgomez
 */
public class BPlusIterator<T> implements Iterator<T>{
    protected int pos = -1;
    protected ImmutableLeafNode<T> node;

    public BPlusIterator( ImmutableLeafNode<T> node ){
        this.node = node;
    }
    
    public BPlusIterator( BPlusLocation<T> loc ){
        node = loc.node;
        pos = loc.pos - 1;
    }

    @Override
    public boolean hasNext(){
        return (pos+1<node.n() || node.right()!=null);
    }

    @Override
    public T next()  throws NoSuchElementException{
        try{
            while(node!=null&&pos+1==node.n()){
                pos = -1;
                node = (ImmutableLeafNode<T>)node.right();
            }
            if(pos+1<node.n()){
                pos++;
            }    
            return node.key(pos);
        }catch( Exception e ){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
    }    
    
}
