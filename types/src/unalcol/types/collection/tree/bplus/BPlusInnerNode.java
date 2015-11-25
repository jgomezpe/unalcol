package unalcol.types.collection.tree.bplus;

import unalcol.types.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.types.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableNode;

public interface BPlusInnerNode<T> extends ImmutableInnerNode<T>, MutableBPlusNode<T>{
    public boolean add( ImmutableNode<T> key );    
    public boolean add( ImmutableNode<T> key, int index );    
    public boolean add( ImmutableNode<T> key, ImmutableBPlus<T> tree );    
    public boolean remove();    
    public boolean remove( int index );    
    public boolean remove( ImmutableNode<T> key, ImmutableBPlus<T> tree );    
}
