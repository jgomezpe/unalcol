package unalcol.collection.tree.bplus;

import unalcol.collection.array.ArrayUtil;
import unalcol.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.collection.tree.bplus.immutable.ImmutableNode;

public interface BPlusInnerNode<T> extends ImmutableInnerNode<T>, BPlusNode<T>{

	public void setNext( ImmutableNode<T>[] next );
    public void setNext( ImmutableNode<T> node, int index );
	
    public default boolean add( ImmutableNode<T> key ){
		return add( key, n() );    	
    }    
    
    public default boolean add( ImmutableNode<T> key, int index ){
    	int n = n();
		if( n < size() ){
			((BPlusNode<T>)key).setParent(this);
			ImmutableNode<T>[] next = next();
			ArrayUtil.insert(n, next, key, index);
			setNext(next);
			setn(n+1);
			return true;
		}
		return false;    	
    }    
    
    public default boolean add( ImmutableNode<T> key, ImmutableBPlus<T> tree ){
		return add( key, ArrayUtil.indexForAddToSortArray(n(), next(), key, tree.node_order()) );
    }   
    
    public default boolean remove(){
		return remove(n()-1);
    }   
    
    public default boolean remove( int index ){
    	int n = n();
		if( 0<=index && index<n ){
			ImmutableNode<T>[] next = next();
			BPlusNode<T> node = (BPlusNode<T>)next[index];
			if(node.left()!=null)
				((BPlusNode<T>)node.left()).setRight(node.right());
			
			if(node.right()!=null)
				((BPlusNode<T>)node.right()).setLeft(node.left());
			
			ArrayUtil.del(n, next, index);
			setn(n-1);
			setNext(next);
			return true;
		}
		return false;
    }   
    
    public default boolean remove( ImmutableNode<T> key, ImmutableBPlus<T> tree ){
		return remove( ArrayUtil.findInSortArray(n(), next(), key, tree.node_order()) );
    }  
    
    // Balance
	@Override
    public default void leftShift(){
        ((BPlusInnerNode<T>)left()).add(this.next(0));
        this.remove(0);
    }
    
    @Override
    public default void rightShift(){
		BPlusInnerNode<T> iright = ((BPlusInnerNode<T>)right());
        iright.add(next(n()-1));
        this.remove();
    }    

    @Override
    public default BPlusNode<T> split(){
    	int n = n();
        @SuppressWarnings("unchecked")
		ImmutableNode<T>[] rnext = new ImmutableNode[size()];
        System.arraycopy(next(), n/2, rnext, 0, n-n/2);
        BPlusInnerNode<T> r = (BPlusInnerNode<T>)this.newInstance(rnext,n-n/2);
        r.setLeft(this);
        r.setRight(this.right());
        if( r.right() != null ){
        	((BPlusNode<T>)r.right()).setLeft(r);
        }
        this.setRight(r);
        this.setn(n/2);
        //r.updateLeftKey();
        return r;
    }
    
	@Override
    public default void merge(){
		int n = n();
		ImmutableNode<T> right = right();
		int nr = right.n();
		ImmutableNode<T>[] next = next();
        System.arraycopy(((BPlusInnerNode<T>)right).next(), 0, next, n, nr );
        setn(n+nr);
        setNext(next);
        setRight(right.right());
    }
      
    public default String toString( int level ){
    	StringBuilder sb = new StringBuilder();
    	for( int i=0; i<level;i++){ sb.append(' '); }
    	level++;
    	int n = n();  
    	ImmutableNode<T>[] next = next();
    	for( int i=0; i<n; i++ ){
    		sb.append('|');
    		sb.append(next[i].toString(level));
    	}
    	return sb.toString();
    }	
}