/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.sort.Order;
import unalcol.sort.Search;
import unalcol.types.collection.Location;
import unalcol.types.collection.SearchCollection;
import unalcol.types.collection.tree.bplus.BPlusInnerNode;
import unalcol.types.collection.tree.bplus.BPlusIterator;
import unalcol.types.collection.tree.bplus.BPlusLocation;
import unalcol.types.collection.tree.bplus.memory.MemoryLeafNode;

/**
 *
 * @author jgomez
 */
public class ImmutableBPlus<T> implements SearchCollection<T> {
    protected ImmutableInnerNode<T> root;
    protected Order<T> order;
    protected ImmutableNodeOrder<T> node_order;
    protected Search<T> search;
    protected Search<ImmutableNode<T>> node_search;

    public ImmutableBPlus( Order<T> order ){
    	this( order, null );
    }
    
    public ImmutableBPlus( Order<T> order, ImmutableInnerNode<T> root ){
        this.root = root;
        this.order = order;
        this.node_order = new ImmutableNodeOrder<>(order);
        this.search = new Search<T>();
        this.node_search = new Search<ImmutableNode<T>>();
    }

    public int search( T[] keys, T key, int n ){
        return search.findRight(keys, 0, n, key, order);
    }

    public Order<T> key_order(){ return order; }
    public Order<ImmutableNode<T>> node_order(){ return node_order; }
    
    @SuppressWarnings("unchecked")
	protected MemoryLeafNode<T> search_aux = new MemoryLeafNode<T>( (T[])new Object[1], 1);
    public int search( ImmutableNode<T>[] keys, T key, int n ){
    	search_aux.remove();
        search_aux.add(key);
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
    
    protected Location<T> find( ImmutableNode<T> node, T data ){
        if( node!=null){
            if( node instanceof BPlusInnerNode){
                ImmutableInnerNode<T> inode = (ImmutableInnerNode<T>)node;
                if(inode.n()>1){
                    int k = search(inode.next(), data, node.n())-1;
                    if( k<0 ) return new BPlusLocation<>(-1,null);
                    return find(inode.next(k), data);
                }else
                    return find(inode.next(0), data);
            }else{
                ImmutableLeafNode<T> lnode = (ImmutableLeafNode<T>)node;
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
    
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	if( root != null )
    		sb.append(root.toString(0));
    	return sb.toString();
    }
}