package unalcol.types.collection.tree.bplus;

import unalcol.types.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableNode;

public interface MutableBPlusNode<T> extends ImmutableNode<T>{
    // Parent
    public void setParent( ImmutableInnerNode<T> node );
    
    // Siblings    
    public void         setLeft( ImmutableNode<T> node );
    public void         setRight( ImmutableNode<T> node );

}
