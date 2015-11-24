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
    protected BPlusNode<T>[] next;
    protected T leftKey;
    
    @SuppressWarnings("unchecked")
	public MemoryInnerNode( int SIZE ){
        next = new BPlusNode[SIZE];
        n = 0;
    }

    public MemoryInnerNode( ImmutableNode<T>[] next, int n ){
        this.next = (BPlusNode<T>[])next;
        this.n = n;
        this.updateLeftKey();
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
    @Override
    public void leftShift(){
        ((BPlusInnerNode<T>)left).add(this.next(0));
        this.remove(0);
    }
    
    @Override
    public void rightShift(){
        BPlusInnerNode<T> iright = ((BPlusInnerNode<T>)right);
        iright.add(next(n()-1));
        this.remove(n()-1);
    }
    
    @Override
    public void merge(){
        System.arraycopy(((BPlusInnerNode<T>)right).next(), 0, next, n(), right.n());
        right = (BPlusNode<T>)right.right();
    }
  
    @Override
    public BPlusNode<T> split(){
        System.out.println("Inner Split");
        @SuppressWarnings("unchecked")
		BPlusNode<T>[] rnext = new BPlusNode[next.length];
        System.arraycopy(next(), n/2, rnext, 0, n-n/2);
        MemoryInnerNode<T> r = new MemoryInnerNode<>(rnext,n-n/2);
        r.updateLeftKey();
/*        r.setRight( right );
        r.setLeft( this );
        if( r.right() != null ){
            r.right().setLeft(r);
        } 
        this.setRight(r); */
        this.setn(n/2);
        return r;
    }
    
    //Keys
    @Override
    public T leftKey(){ return leftKey; }
    
    @Override
    public final T updateLeftKey(){
        leftKey = (n>0 &&next[0]!=null)?next[0].leftKey():null;
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
    public boolean append(BPlusNode<T> node ){
        next[n] = node;
        n++;
        fix(n-1);
        return true;
    }
    
    @Override
    public boolean insert( int pos, BPlusNode<T> node ){ 
        if( pos < 0 || pos > n || isFull() ) return false;
        ArrayUtil.insert(n, next, node, pos);
        n++;
        fix(pos);
        if(pos==0) leftKey = node.leftKey();
        return true;
    }
    
    
    @Override
    public boolean remove( int pos ){
        if( 0<=pos && pos<n ){
            BPlusNode<T> node = next[pos];
            if(node.right()!=null){
                node.right().setLeft(node.left());
            }
            if(node.left()!=null){
                node.left().setRight(node.right());
            }
            ArrayUtil.del(n, next, pos);
            n--;
            if(pos==0)
                updateLeftKey();            
            return true;
        }else{
            return false;
        }
    }

    
	@SuppressWarnings("unchecked")
	@Override
	public ImmutableLeafNode<T> mostLeft() {
        if(next[0] instanceof BPlusInnerNode)
            return ((BPlusInnerNode<T>)next[0]).mostLeft();
        else
            return (BPlusLeafNode<T>)next[0];
	}

	@Override
	public boolean add(ImmutableNode<T> key, ImmutableBPlus<T> tree) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(ImmutableNode<T> key, ImmutableBPlus<T> tree) {
		// TODO Auto-generated method stub
		return false;
	}
    
}