package unalcol.types.collection.tree.bplus;

import unalcol.sort.Order;
import unalcol.types.collection.Location;
import unalcol.types.collection.MutableCollection;
import unalcol.types.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.types.collection.tree.bplus.immutable.ImmutableNode;

public abstract class BPlus<T> extends ImmutableBPlus<T> implements MutableCollection<T> {
    public BPlus( int n, Order<T> order ){
        super( order );
        this.root = innerNode(n+1);
        ((BPlusInnerNode<T>)this.root).add(leafNode(n+1), this);
    }

    public abstract BPlusInnerNode<T> innerNode( int n );
    
    public abstract BPlusLeafNode<T> leafNode( int n );
    
    public void balance( BPlusNode<T> node ){
    	node.balance(this);
    	if( root.parent() != null ){
    		root = root.parent();
    	}
	}	

    @SuppressWarnings("unchecked")
	public boolean del( ImmutableNode<T> node, T data ){
    	boolean flag = ( node==null);
    	if( !flag ) return false;
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
            if( flag ) balance((BPlusNode<T>)node);
            /*
            int k = search(lnode.keys(), data, node.n())-1;
            if( k<0 ) return false;
            if( order.compare(data, lnode.key(k)) == 0 ){
                flag = lnode.remove(data, this);
            }else{  flag =false;  }*/
        }
        return flag;
    }
    
    @Override
    public boolean del( T key ){
        return del(root, key);
    }

    @SuppressWarnings("unchecked")
	public boolean add( ImmutableNode<T> node, T data ){
    	boolean flag;
        if( node instanceof BPlusInnerNode){
            BPlusInnerNode<T> inode = (BPlusInnerNode<T>)node;
            ImmutableNode<T> theNode;
            if(inode.n()>1){
                int k = search(inode.next(), data, node.n())-1;
                if( k<0 ) k=0;
                theNode = inode.next(k);
            }else{
                theNode = inode.next(0);
            }    
            flag = add(theNode, data);
        }else{
            BPlusLeafNode<T> lnode = (BPlusLeafNode<T>)node;
            flag = lnode.add(data, this);
            balance((BPlusNode<T>)node);
/*            System.out.println(lnode!=null);
            int k = search(lnode.keys(), data, node.n());
            if( k<0 ) return false;
            if( k>0 && order.compare(data, lnode.key(k-1)) == 0 ){
                return false;
            }else{
                return lnode.insert(k, data);
            }*/    
        }
        return flag;
    }
    
    @Override
    public boolean add( T key ){
        return add(root,key);
    }


    @Override
    public void clear() {
    }

    @Override
    public boolean del(Location<T> locator) {
        return false; 
    }    
}
