package unalcol.types.collection.tree.bplus;

import unalcol.types.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.types.collection.tree.bplus.immutable.ImmutableLeafNode;

public interface BPlusLeafNode<T> extends ImmutableLeafNode<T>, MutableBPlusNode<T> {
    public boolean add( T key, ImmutableBPlus<T> tree );    
    public boolean remove( T key, ImmutableBPlus<T> tree );    
}
