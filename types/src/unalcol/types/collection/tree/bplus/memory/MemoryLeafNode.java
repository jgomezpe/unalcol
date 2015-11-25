/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.tree.bplus.memory;
import unalcol.types.collection.array.ArrayUtil;
import unalcol.types.collection.tree.bplus.*;
import unalcol.types.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.types.collection.tree.bplus.immutable.ImmutableLeafNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableNode;

/**
 *
 * @author jgomez
 */
public class MemoryLeafNode<T> extends MemoryNode<T> implements BPlusLeafNode<T>{
    protected T[] keys;
    
    @SuppressWarnings("unchecked")
	public MemoryLeafNode( int SIZE ){
        keys = (T[])new Object[SIZE];
        n = 0;
    }
    
    public MemoryLeafNode( T[] _keys, int _n ){
        keys = _keys;
        n = _n;
    }
    
    @Override
    public ImmutableNode<T> newInstance(int SIZE){
        return new MemoryLeafNode<>(SIZE);
    }
    
    @Override
    public ImmutableLeafNode<T> newInstance( T[] keys, int n ){
       return new MemoryLeafNode<>(keys, n);
    }

    // Balance
    @Override
    public void leftShift(){
        ((MemoryLeafNode<T>)left).add(key(0));
        this.remove(0);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void rightShift(){
        ((BPlusLeafNode<T>)right).add(key(n()-1),0);
        this.remove();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void merge(){
        System.arraycopy(((BPlusLeafNode<T>)right).keys(), 0, keys, n(), right.n());
        n += right.n();
        right = (MemoryNode<T>)right.right();
        if( right != null ) right.setLeft(this);
    }

    @Override
    public BPlusNode<T> split(){
        @SuppressWarnings("unchecked")
		T[] rkeys = (T[])new Object[keys.length];
        System.arraycopy(keys, n/2, rkeys, 0, n-n/2);
        MemoryLeafNode<T> r = new MemoryLeafNode<>(rkeys,n-n/2);
        r.setLeft(this);
        r.setRight(this.right());
        if( r.right() != null ){
        	((BPlusNode<T>)r.right()).setLeft(r);
        }
        this.setRight(r);
        this.setn(n/2);
        return r;
    }
    
    //Keys
    @Override
    public T leftKey(){ return n()>0?keys[0]:null; }
    
    @Override
    public T updateLeftKey(){
        return leftKey();
    }
    
    @Override
    public T[] keys(){
        return keys;
    }
        
    @Override
    public T key( int index ){
        return keys[index];
    }
    
    // Size
    @Override
    public int size(){
        return keys.length;
    }

	@Override
	public boolean add(T key) {
		if(n<keys.length){
			keys[n] = key;
			n++;
			return true;
		}
		return false;
	}

	@Override
	public boolean add(T key, int index) {
		if( n < keys.length ){
			ArrayUtil.insert(n, keys, key, index);
			n++;
			return true;
		}
		return false;
	}

	@Override
	public boolean add(T key, ImmutableBPlus<T> tree) {
		boolean flag = ArrayUtil.addToSortArray(n, keys, key, tree.key_order());
		if( flag ) n++;
		return flag;
	}

	@Override
	public boolean remove() {
		if( n>0 ){
			n--;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if( n>0 && index >=0 && index < n){
	        ArrayUtil.del(n, keys, index);
			n--;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(T key, ImmutableBPlus<T> tree) {
        boolean flag = ArrayUtil.removeFromSortArray(n, keys, key, tree.key_order() );
        if( flag ) n--;
        return flag;
	}
	
    public String toString( int level ){
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