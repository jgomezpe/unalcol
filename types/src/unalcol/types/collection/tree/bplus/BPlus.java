/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.tree.bplus;
import unalcol.types.collection.*;
import unalcol.sort.*;
import unalcol.types.collection.tree.bplus.memory.*;

/**
 *
 * @author jgomez
 */
public class BPlus<T> extends ImmutableBPlus<T> implements MutableCollection<T> {
    public BPlus( int n, Order<T> order,
                  BPlusInnerNode<T> root, BPlusLeafNode<T> leaf ){
        super( order, (BPlusInnerNode<T>)root.newInstance(n+1) );
        this.root.insert(0, leaf.newInstance(n));
    }

    public BPlus( int n, Order<T> order ){
        this( n, order, new Search<T>() );
    }

    public BPlus( int n, Order<T> order, Search<T> search ){
        this( n, order, 
              new MemoryInnerNode<T>(n+1), new MemoryLeafNode<T>(n) );
    }
    
    public void balance( BPlusInnerNode<T> node, int k ){
        BPlusNode<T> theNode = node.next(k);
        if(theNode.underFill()){
            if( k<node.n()-1 ){
                if( theNode.n() + node.next(k+1).n() <= 2*theNode.underFillSize() ){
                    // merge next(k) with next(k+1) and remove node next(k+1)
                    theNode.merge();
                    node.remove(k+1);
                }else{
                    // move left key of next(k+1) to next(k)
                    node.next(k+1).leftShift();
                }
            }else{
                if( node.n()>1 ){
                    if(theNode.n() + node.next(k-1).n() <= 2*theNode.underFillSize() ){
                        // merge next(k-1) with next(k) and remove node next(k)
                        node.next(k-1).merge();
                        node.remove(k);
                    }else{
                        // move right key of next(k-1) to next(k)
                        node.next(k-1).rightShift();
                    }
                }
            }
        }else{
            if( theNode.isFull() ){
                theNode.split();
                node.insert(k+1, theNode.right());
            }
        }
    }
    
    public boolean del( BPlusNode<T> node, T data ){
        if( node==null) return false;
        if( node instanceof BPlusInnerNode){
            BPlusInnerNode<T> inode = (BPlusInnerNode<T>)node;
            BPlusNode<T> theNode;
            if(inode.n()>1){
                int k = search(inode.next(), data, node.n())-1;
                if( k<0 ) return false;
                theNode = inode.next(k);
                if( del(theNode, data) ){
                    balance(inode,k);
                    return true;
                }else{ return false; }
            }else
                theNode = inode.next(0);
                if( del(theNode, data) ){
                    balance(inode,0);
                    return true;
                }else{ return false; }
        }else{
            BPlusLeafNode<T> lnode = (BPlusLeafNode<T>)node;
            int k = search(lnode.keys(), data, node.n())-1;
            if( k<0 ) return false;
            if( order.compare(data, lnode.key(k)) == 0 ){
                return lnode.remove(k);
            }else
                return false;
        }
    }
    
    @Override
    public boolean del( T key ){
        return del(root, key);
    }

    public boolean add( BPlusNode<T> node, T data ){
        if( node instanceof BPlusInnerNode){
            BPlusInnerNode<T> inode = (BPlusInnerNode<T>)node;
            BPlusNode<T> theNode;
            if(inode.n()>1){
                int k = search(inode.next(), data, node.n())-1;
                if( k<0 ) k=0;
                theNode = inode.next(k);
                if( add(theNode, data) ){
                    balance(inode,k);
                    return true;
                }else{ return false; }
            }else
                theNode = inode.next(0);
                if( add(theNode, data) ){
                    balance(inode,0);
                    return true;
                }else{ return false; }
        }else{
            BPlusLeafNode<T> lnode = (BPlusLeafNode<T>)node;
            System.out.println(lnode!=null);
            int k = search(lnode.keys(), data, node.n());
            if( k<0 ) return false;
            if( k>0 && order.compare(data, lnode.key(k-1)) == 0 ){
                return false;
            }else{
                return lnode.insert(k, data);
            }    
        }
    }
    
    @Override
    public boolean add( T key ){
        boolean flag = add(root,key);
        if(flag && root.isFull()){
            BPlusInnerNode<T> newRoot = (BPlusInnerNode<T>)root.newInstance(root.size());
            root.split();            
            BPlusNode<T> rroot = root.right(); 
            newRoot.append(root);
            newRoot.append(rroot);
            root = newRoot;
        }
        return flag;
    }


    @Override
    public void clear() {
    }

    @Override
    public boolean del(Location<T> locator) {
        return false; 
    }    
}
