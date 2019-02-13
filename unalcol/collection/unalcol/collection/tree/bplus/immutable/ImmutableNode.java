package unalcol.collection.tree.bplus.immutable;

public interface ImmutableNode<T> {
    public ImmutableNode<T> newInstance(int SIZE);
    
    //key's methods
    // Left key
    public T leftKey();  
    public T updateLeftKey();

    // Size analysis
    public int n();
    public int size();        
    public default int underFillSize(){ return size()/3; }
    public default boolean isFull(){ return n()==size(); }
    public default boolean underFill(){ return n()<underFillSize(); }

    // Parent
    public ImmutableInnerNode<T> parent();    
    
    // Siblings    
    public ImmutableNode<T> left();
    public ImmutableNode<T> right();
    
    public String toString( int level );
}
