package unalcol.collection.tree.bplus.immutable;

public interface ImmutableLeafNode<T> extends ImmutableNode<T>{
	
    public ImmutableLeafNode<T> newInstance( T[] keys, int n );
    
    //key's methods
    public T[] keys();    
    
    public default T key( int index ){ return keys()[index]; }
    
    //Keys
    @Override
    public default T leftKey(){ return n()>0?key(0):null; }
    
    @Override
    public default T updateLeftKey(){
        return leftKey();
    }    
}