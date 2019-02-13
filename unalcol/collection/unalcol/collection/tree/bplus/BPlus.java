package unalcol.collection.tree.bplus;

import unalcol.sort.Order;
import unalcol.collection.Mutable;
import unalcol.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.collection.tree.bplus.immutable.ImmutableNode;

public abstract class BPlus<T> extends ImmutableBPlus<T> implements Mutable<BPlusLocation<T>,T> {
	public static final int MIN_BRANCHING = 6;
	protected int BRANCHING;
    public BPlus( Order order ){
        this( MIN_BRANCHING, order );
    }

    public BPlus( int BRANCHING, Order order ){
        super( order );
        this.BRANCHING = BRANCHING<MIN_BRANCHING?MIN_BRANCHING:BRANCHING;
    }

    public abstract BPlusInnerNode<T> innerNode();
    
    public abstract BPlusLeafNode<T> leafNode();
    
	public boolean del( ImmutableNode<T> node, T data ){
    	if( node==null ) return false;
    	boolean flag;
        if( node instanceof BPlusInnerNode){
            BPlusInnerNode<T> inode = (BPlusInnerNode<T>)node;
            ImmutableNode<T> theNode;
            if(inode.n()>1){
                int k = search(inode.next(), data, node.n())-1;
                if( k<0 ) return false;
                theNode = inode.next(k);
            }else{
                theNode = inode.next(0);
            }
            flag = del(theNode, data);            
        }else{
            BPlusLeafNode<T> lnode = (BPlusLeafNode<T>)node;
            flag = lnode.remove(data, this);
            if( flag ) ((BPlusNode<T>)node).checkEmpty(this);
        }
        return flag;
    }
    
    @Override
    public boolean del( T key ){
        return del(root, key);
    }

	public BPlusLeafNode<T> locate( ImmutableNode<T> node, T data ){
        if( node instanceof BPlusInnerNode){
            BPlusInnerNode<T> inode = (BPlusInnerNode<T>)node;
            int k = search(inode.next(), data, node.n())-1;
            if( k<0 ) return (BPlusLeafNode<T>)inode.mostLeft();
            return locate(inode.next(k), data);
        }else{
        	return (BPlusLeafNode<T>)node;
        }
	}
	    
	@Override
    public boolean add( T key ){
    	if( this.root == null ){
            this.root = innerNode();
            BPlusLeafNode<T> leaf = leafNode();
            leaf.add(key);
            ((BPlusInnerNode<T>)this.root).add(leaf);
    		return true;
    	}
    	BPlusLeafNode<T> leaf = locate(this.root, key);
        if( leaf.add(key, this) ){
        	((BPlusNode<T>)leaf).checkFull(this);
        	if( root.parent() != null ){
        		root = root.parent();
        	}
        	return true;
        }
        return false;
        //return add(root,key);
    }

    @Override
    public void clear() {
    	root = null;
    }

    @Override
    public boolean remove(BPlusLocation<T> locator) {
        return false; 
    }    
}