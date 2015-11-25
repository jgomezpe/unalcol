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
public class MemoryInnerNode<T> extends MemoryNode<T> implements BPlusInnerNode<T>{
    protected ImmutableNode<T>[] next;
    protected T leftKey;
    
    @SuppressWarnings("unchecked")
	public MemoryInnerNode( int SIZE ){
        next = new BPlusNode[SIZE];
        n = 0;
    }

    public MemoryInnerNode( ImmutableNode<T>[] next, int n ){
        this.next = next;
        this.n = n;
        for( int i=0; i<n; i++){
        	((BPlusNode<T>)next[i]).setParent(this);
        }
        //this.updateLeftKey();
    }

    @Override
    public ImmutableNode<T> newInstance(int SIZE){
        return new MemoryInnerNode<T>(SIZE);
    }
    
    @Override
    public BPlusInnerNode<T> newInstance( ImmutableNode<T>[] next, int n ){
       return new MemoryInnerNode<T>(next,n);
    }
    
    // Balance
    @SuppressWarnings("unchecked")
	@Override
    public void leftShift(){
        ((BPlusInnerNode<T>)left).add(this.next(0));
        this.remove(0);
    }
    
    @Override
    public void rightShift(){
        @SuppressWarnings("unchecked")
		BPlusInnerNode<T> iright = ((BPlusInnerNode<T>)right);
        iright.add(next(n()-1));
        this.remove();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void merge(){
        System.arraycopy(((BPlusInnerNode<T>)right).next(), 0, next, n(), right.n());
        right = (BPlusNode<T>)right.right();
    }
  
    @Override
    public BPlusNode<T> split(){
        @SuppressWarnings("unchecked")
		BPlusNode<T>[] rnext = new BPlusNode[next.length];
        System.arraycopy(next(), n/2, rnext, 0, n-n/2);
        MemoryInnerNode<T> r = new MemoryInnerNode<>(rnext,n-n/2);
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
    
    //Keys
    @Override
    public T leftKey(){ return updateLeftKey(); }
    
    @Override
    public final T updateLeftKey(){
        leftKey = (n>0 &&next[0]!=null)?next[0].updateLeftKey():null;
        return leftKey;
    }    

    // Size
    @Override
    public int size(){
        return next.length;
    }
    
    @Override
    public ImmutableNode<T>[] next(){
        return next;
    }

    @Override
    public ImmutableNode<T> next(int i){
        return next[i];
    }
    
	@Override
	public ImmutableLeafNode<T> mostLeft() {
        if(next[0] instanceof BPlusInnerNode)
            return ((BPlusInnerNode<T>)next[0]).mostLeft();
        else
            return (BPlusLeafNode<T>)next[0];
	}

	@Override
	public boolean add(ImmutableNode<T> key) {
		return add( key, n );
	}

	@Override
	public boolean add(ImmutableNode<T> key, int index) {
		if( n < next.length ){
			((BPlusNode<T>)key).setParent(this);
			ArrayUtil.insert(n, next, key, index);
			n++;
			return true;
		}
		return false;
	}

	@Override
	public boolean add(ImmutableNode<T> key, ImmutableBPlus<T> tree) {
		return add( key, ArrayUtil.indexForAddToSortArray(n, next, key, tree.node_order()) );
	}

	@Override
	public boolean remove() {
		return remove(n-1);
	}

	@Override
	public boolean remove(int index) {
		if( 0<=index && index<n ){
			if(next[index].left()!=null)
				((BPlusNode<T>)next[index].left()).setRight(next[index].right());
			
			if(next[index].right()!=null)
				((BPlusNode<T>)next[index].right()).setLeft(next[index].left());
			
			ArrayUtil.del(n, next, index);
			n--;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(ImmutableNode<T> key, ImmutableBPlus<T> tree) {
		return remove( ArrayUtil.findInSortArray(n, next, key, tree.node_order()) );
	}
	
    public String toString( int level ){
    	StringBuilder sb = new StringBuilder();
    	for( int i=0; i<level;i++){ sb.append(' '); }
    	level++;
    	for( int i=0; i<n; i++ ){
    		sb.append('|');
    		sb.append(next[i].toString(level));
    	}
    	return sb.toString();
    }
}