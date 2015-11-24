package unalcol.types.collection.tree.bplus.immutable;

public interface ImmutableNode<T> {
    public ImmutableNode<T> newInstance(int SIZE);
    
    //key's methods
    // Left key
    public T leftKey();  
    public T updateLeftKey();

    // Size analysis
    public int n();
    public void setn( int n );            
    public int size();        
    public int underFillSize();
    public boolean isFull();
    public boolean underFill();

    // Parent
    public ImmutableInnerNode<T> parent();    
    
    // Siblings    
    public ImmutableNode<T> left();
    public ImmutableNode<T> right();
}
