package unalcol.collection.tree.bplus;

import unalcol.collection.array.ArrayUtil;
import unalcol.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.collection.tree.bplus.immutable.ImmutableLeafNode;

public interface BPlusLeafNode<T> extends ImmutableLeafNode<T>, BPlusNode<T>{
    public void setKeys( T[] keys );
    public void setKey( T key, int index );

    public default boolean add( T key ){
		return add( key, n() );
	}
	
	public default boolean add( T key, int index ){
		int n = n();
		if( n < size() ){
			T[] keys = keys();
			ArrayUtil.insert(n, keys, key, index);
			setn(n+1);
			setKeys(keys);
			return true;
		}
		return false;
		
	}
	
    public default boolean add( T key, ImmutableBPlus<T> tree ){
    	int n = n();
    	T[] keys = keys();
		boolean flag = ArrayUtil.addToSortArray(n, keys, key, tree.key_order());
		if( flag ){ 
			setn(n+1);
			setKeys(keys);
		}
		return flag;    	
    }   
    
	public default boolean remove(){
		int n = n();
		if( n>0 ){
			setn(n-1);
			return true;
		}
		return false;		
	}
	
	public default boolean remove( int index ){
		int n = n();
		if( n>0 && index >=0 && index < n){
			T[] keys = keys();
	        ArrayUtil.del(n, keys, index);
			setn(n-1);
			setKeys(keys);
			return true;
		}
		return false;		
	}
	
    public default boolean remove( T key, ImmutableBPlus<T> tree ){
    	int n = n();
    	T[] keys = keys();
        boolean flag = ArrayUtil.removeFromSortArray(n, keys, key, tree.key_order() );
        if( flag ){
        	setn(n-1);
        	setKeys(keys);
        }
        return flag;
    }
    
    // Balance
    @Override
    public default void leftShift(){
        ((BPlusLeafNode<T>)left()).add(key(0));
        this.remove(0);
    }
    
	@Override
    public default void rightShift(){
        ((BPlusLeafNode<T>)right()).add(key(n()-1),0);
        this.remove();
    }    

	@Override
    public default void merge(){
		int n = n();
		T[] keys = keys();
		BPlusLeafNode<T> right = (BPlusLeafNode<T>)right();
		int nr = right.n(); 
        System.arraycopy(right.keys(), 0, keys, n, nr);
        setn( n + nr);
        setKeys(keys);
        setRight(right.right());
        if( right() != null ) ((BPlusNode<T>)right()).setLeft(this);
    }

    @Override
    public default BPlusNode<T> split(){
    	int n = n();
    	T[] keys = keys();
        @SuppressWarnings("unchecked")
		T[] rkeys = (T[])new Object[keys.length];
        System.arraycopy(keys, n/2, rkeys, 0, n-n/2);
        BPlusLeafNode<T> r = (BPlusLeafNode<T>)newInstance(rkeys,n-n/2);
        r.setLeft(this);
        r.setRight(this.right());
        if( r.right() != null ){
        	((BPlusNode<T>)r.right()).setLeft(r);
        }
        this.setRight(r);
        this.setn(n/2);
        return r;
    }    	

    public default String toString( int level ){
    	int n = n();
    	T[] keys = keys();
    	StringBuilder sb = new StringBuilder();
    	for( int i=0; i<level;i++){ sb.append(' '); }
    	for( int i=0; i<n; i++ ){
    		sb.append(' ');
    		sb.append(keys[i]);
    	}
    	sb.append('\n');
    	return sb.toString();
    }    
    
}