package unalcol.collection.tree.bplus.immutable;

public interface ImmutableInnerNode<T> extends ImmutableNode<T>{
    
	public ImmutableInnerNode<T> newInstance( ImmutableNode<T>[] nodes, int n );        
    
	// Next methods
    public ImmutableNode<T>[]   next();    
 
    public default ImmutableNode<T> next(int i){ return next()[i]; }

    public default ImmutableLeafNode<T> mostLeft(){
    	ImmutableNode<T> node = next(0);
        if(node instanceof ImmutableInnerNode)
            return ((ImmutableInnerNode<T>)node).mostLeft();
        else
            return (ImmutableLeafNode<T>)node;    	
    }

    //Keys
    @Override
    public default T leftKey(){ return updateLeftKey(); }
    
    @Override
    public default T updateLeftKey(){
    	ImmutableNode<T> node = next(0);
        T leftKey = (n()>0 && node !=null)?node.updateLeftKey():null;
        return leftKey;
    }
}