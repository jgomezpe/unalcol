/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.tree.bplus.memory;
import unalcol.types.collection.array.ArrayUtil;
import unalcol.types.collection.tree.bplus.*;

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
    public BPlusNode<T> newInstance(int SIZE){
        return new MemoryLeafNode<>(SIZE);
    }
    
    @Override
    public BPlusLeafNode<T> newInstance( T[] keys, int n ){
       return new MemoryLeafNode<>(keys, n);
    }

    // Balance
    @Override
    public void leftShift(){
        ((BPlusLeafNode<T>)left).append(key(0));
        this.remove(0);
    }
    
    @Override
    public void rightShift(){
        BPlusLeafNode<T> lright = ((BPlusLeafNode<T>)right);
        lright.insert(0,key(n()-1));
        this.remove(n()-1);
    }
    
    @Override
    public void merge(){
        System.arraycopy(((BPlusLeafNode<T>)right).keys(), 0, keys, n(), right.n());
        right = right.right();
    }

    @Override
    public void split(){
        System.out.println("Leaf Split");
        @SuppressWarnings("unchecked")
		T[] rkeys = (T[])new Object[keys.length];
        System.arraycopy(keys, n/2, rkeys, 0, n-n/2);
        MemoryLeafNode<T> r = new MemoryLeafNode<>(rkeys,n-n/2);
        r.setRight( right );
        r.setLeft( this );
        if( r.right() != null ){
            r.right().setLeft(r);
        }
        this.setRight(r);
        this.setn(n/2);
        System.out.println( r.size() );
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

    @Override
    public boolean insert( int pos, T key ){
        ArrayUtil.insert(n, keys, key, pos);
        n++;
        return true;
    }
    
    @Override
    public boolean remove( int pos ){
        ArrayUtil.del(n, keys, pos);
        n--;
        return true;
    }
    
    @Override
    public boolean append( T key ){
        keys[n] = key;
        n++;
        return true;
    }
    
    @Override
    public void set( int i, T key ){
        keys[i] = key;
    }
    
    // Size
    @Override
    public int size(){
        return keys.length;
    }
    
    @Override
    public boolean isFull(){
        return n==size();
    }

    @Override 
    public int underFillSize(){
        return size()/3;
    }
    
    @Override
    public boolean underFill(){
        return n <= underFillSize();
    }
}
