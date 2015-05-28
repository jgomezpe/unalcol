/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.sort.Order;
import unalcol.sort.Search;
import unalcol.types.collection.Location;
import unalcol.types.collection.SearchCollection;
import unalcol.types.collection.tree.bplus.memory.MemoryLeafNode;

/**
 *
 * @author jgomez
 */
public class ImmutableBPlus<T> implements SearchCollection<T> {
    protected BPlusInnerNode<T> root;
    protected Order<T> order;
    protected BPlusNodeOrder<T> node_order;
    protected Search<T> search;
    protected Search<BPlusNode<T>> node_search;

    public ImmutableBPlus( Order<T> order, BPlusInnerNode<T> root ){
        this.root = root;
        this.order = order;
        this.node_order = new BPlusNodeOrder<>(order);
        this.search = new Search<>();
        this.node_search = new Search<>();
    }

    public int search( T[] keys, T key, int n ){
        return search.findRight(keys, 0, n, key, order);
    }

    @SuppressWarnings("unchecked")
	protected BPlusLeafNode<T> search_aux = new MemoryLeafNode<>( (T[])new Object[1], 1);
    public int search( BPlusNode<T>[] keys, T key, int n ){
        search_aux.set(0, key);
        return node_search.findRight(keys, 0, n, search_aux, node_order);
    }

    @Override
    public boolean isEmpty() {
        return root.mostLeft().n()==0;
    }

    @Override
    public Iterator<T> iterator() {
        return new BPlusIterator<>(root.mostLeft());
    }

    
    @Override
    public boolean contains(T data) {
        try{
        BPlusLocation<T> loc = (BPlusLocation<T>)find(data);
            return( order.compare(loc.get(), data) == 0 );
        }catch( NoSuchElementException e ){
            return false;
        }
    }    
    
    protected Location<T> find( BPlusNode<T> node, T data ){
        if( node!=null){
            if( node instanceof BPlusInnerNode){
                BPlusInnerNode<T> inode = (BPlusInnerNode<T>)node;
                if(inode.n()>1){
                    int k = search(inode.next(), data, node.n())-1;
                    if( k<0 ) return new BPlusLocation<>(-1,null);
                    return find(inode.next(k), data);
                }else
                    return find(inode.next(0), data);
            }else{
                BPlusLeafNode<T> lnode = (BPlusLeafNode<T>)node;
                return new BPlusLocation<>(search(lnode.keys(), data, node.n())-1, lnode);
            }
        }    
        return new BPlusLocation<>(-1,null);
    }

    @Override
    public Location<T> find(T data) {
        return find(root,data);
    }

    @Override
    public Iterator<T> iterator(Location<T> locator) {
        return new BPlusIterator<T>((BPlusLocation<T>)locator);
    }   
}