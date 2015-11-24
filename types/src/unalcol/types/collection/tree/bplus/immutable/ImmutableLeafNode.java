package unalcol.types.collection.tree.bplus.immutable;

public interface ImmutableLeafNode<T> extends ImmutableNode<T>{
    public ImmutableLeafNode<T> newInstance( T[] keys, int n );        
    //key's methods
    public T[] keys();    
    public T key( int index );
}