package unalcol.types.collection.tree.bplus.immutable;

public interface ImmutableInnerNode<T> extends ImmutableNode<T>{
    public ImmutableInnerNode<T> newInstance( ImmutableNode<T>[] nodes, int n );        
    // Next methods
    public ImmutableNode<T>[]   next();    
    public ImmutableNode<T>     next(int i);
    public ImmutableLeafNode<T> mostLeft();

}
