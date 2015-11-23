package unalcol.types.collection.tree.bplus.file;

import java.io.IOException;
import java.io.RandomAccessFile;

import unalcol.types.collection.array.ArrayUtil;
import unalcol.types.collection.tree.bplus.BPlusInnerNode;
import unalcol.types.collection.tree.bplus.BPlusLeafNode;
import unalcol.types.collection.tree.bplus.BPlusNode;
import unalcol.types.collection.tree.bplus.memory.MemoryInnerNode;
import unalcol.types.collection.tree.bplus.memory.MemoryNode;

public class FileInnerNode<T>extends FileNode<T> implements BPlusInnerNode<T>{
    protected T leftKey;
    
    @SuppressWarnings("unchecked")
	public FileInnerNode( RandomAccessFile index_file, int SIZE ){
        super(index_file, SIZE);
    }

    @Override
    public BPlusNode<T> newInstance(int SIZE){
        return new FileInnerNode<>(index_file, SIZE);
    }
    
    // Balance
    @Override
    public void leftShift(){
        ((BPlusInnerNode<T>)left()).append(next(0));
        this.remove(0);
    }
    
    @Override
    public void rightShift(){
        BPlusInnerNode<T> iright = ((BPlusInnerNode<T>)right());
        iright.insert(0,next(n()-1));
        this.remove(n()-1);
    }
    
    @Override
    public void merge(){
        System.arraycopy(((BPlusInnerNode<T>)right()).next(), 0, next, n(), right().n());
        setRight(right().right());
    }
  
    @Override
    public void split(){
    	int n = n();
        System.out.println("File Inner Split");
        @SuppressWarnings("unchecked")
		BPlusNode<T>[] rnext = new BPlusNode[next.length];
        System.arraycopy(next(), n/2, rnext, 0, n-n/2);
        MemoryInnerNode<T> r = new MemoryInnerNode<>(rnext,n-n/2);
        r.updateLeftKey();
/*        r.setRight( right );
        r.setLeft( this );
        if( r.right() != null ){
            r.right().setLeft(r);
        } */
        this.setRight(r);
        this.setn(n/2);
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
    
    @Override
    public BPlusNode<T>[] next(){
        return next;
    }

    @Override
    public BPlusNode<T> next(int i){
    	try{
    		index_file.seek(pos+SIZE_BYTES+(i+3)*REFERENCE_BYTES);
    		return new index_file.readLong();
    	}catch(IOException e){}
    	return -1; 
    }
    
    public void fix( int pos ){
        BPlusNode<T> node = next(pos);
        node.setParent(this);
        if(n()==1){
            if(left()!=null){
                BPlusInnerNode<T> ileft = (BPlusInnerNode<T>)left();
                BPlusNode<T> rleft = ileft.next(ileft.n()-1);
                node.setLeft(rleft);
                node.setRight(rleft.right());
            }else{
                if( right() != null ){
                    BPlusInnerNode<T> iright = (BPlusInnerNode<T>)right();
                    BPlusNode<T> rright = iright.next(0);
                    node.setRight(rright);                    
                    node.setLeft(rright.left());                    
                }else{
                    node.setLeft(null);
                    node.setRight(null);
                }
            }
            node.updateLeftKey();
        }else{
            if( pos<n()-1 ){
                if( next(pos+1).left() != node )
                    node.setLeft(next(pos+1).left());
                node.setRight(next(pos+1));
            }else{
                node.setLeft(next(pos-1));
                if( next(pos-1).right() != node )
                    node.setRight(next(pos-1).right());
            }
        }
        
        if( node.right() != null ){
            node.right().setLeft(node);
        }
        if(node.left()!=null){
            node.left().setRight(node);
        }
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

    
    @Override
    public BPlusLeafNode<T> mostLeft(){
        if(next[0] instanceof BPlusInnerNode)
            return ((BPlusInnerNode<T>)next[0]).mostLeft();
        else
            return (BPlusLeafNode<T>)next[0];
    }
    
    @Override
    public void set(int pos, BPlusNode<T> key ){
        next[pos] = key;
    } 
}
