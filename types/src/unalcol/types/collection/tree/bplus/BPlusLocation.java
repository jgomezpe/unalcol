/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus;

import java.util.NoSuchElementException;
import unalcol.types.collection.Location;
import unalcol.types.collection.tree.bplus.immutable.ImmutableLeafNode;

/**
 *
 * @author jgomez
 */
public class BPlusLocation<T> implements Location<T> {
    protected int pos = -1;
    protected ImmutableLeafNode<T> node;
    
    public BPlusLocation(int _pos, ImmutableLeafNode<T> _node){
        pos = _pos;
        node = _node;
    }
    
    @Override
    public T get() throws NoSuchElementException {
        try{
            return node.key(pos);
        }catch( Exception e ){
            throw new NoSuchElementException("No such element");
        }    
    }
    
}
