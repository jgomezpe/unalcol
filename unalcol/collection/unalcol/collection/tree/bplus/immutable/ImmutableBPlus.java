/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection.tree.bplus.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.sort.Order;
import unalcol.collection.Collection;
import unalcol.collection.Search;
import unalcol.collection.array.BinarySearch;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.tree.bplus.BPlusIterator;
import unalcol.collection.tree.bplus.BPlusLocation;
import unalcol.collection.tree.bplus.memory.MemoryLeafNode;

/**
 *
 * @author jgomez
 */
public class ImmutableBPlus<T> implements Search<BPlusLocation<T>,T> {
    protected ImmutableInnerNode<T> root;
    protected Order order;
    protected ImmutableNodeOrder<T> node_order;
    protected BinarySearch<T> search;
    protected BinarySearch<ImmutableNode<T>> node_search;

    public ImmutableBPlus( Order order ){
    	this( order, null );
    }
    
    public ImmutableBPlus( Order order, ImmutableInnerNode<T> root ){
        this.root = root;
        this.order = order;
        this.node_order = new ImmutableNodeOrder<T>(order);
        this.search = new BinarySearch<T>(null,order);
        this.node_search = new BinarySearch<ImmutableNode<T>>(null,node_order);
    }

    public int search( T[] keys, T key, int n ){
    	search.set(keys);
        return search.findRight(0, n, key);
    }

    public Order key_order(){ return order; }
    public Order node_order(){ return node_order; }
    
    @SuppressWarnings("unchecked")
	protected MemoryLeafNode<T> search_aux = new MemoryLeafNode<T>( (T[])new Object[1], 1);
    public int search( ImmutableNode<T>[] keys, T key, int n ){
    	search_aux.remove();
        search_aux.add(key);
        node_search.set(keys);
        return node_search.findRight(0, n, search_aux);
    }

    @Override
    public boolean isEmpty() {
        return root.mostLeft().n()==0;
    }

    @Override
    public Iterator<T> iterator() {
        return new BPlusIterator<>(root.mostLeft());
    }

    
    protected BPlusLocation<T> find( ImmutableNode<T> node, T data ){
        if( node!=null){
            if( node instanceof ImmutableInnerNode){
                ImmutableInnerNode<T> inode = (ImmutableInnerNode<T>)node;
                if(inode.n()>1){
                    int k = search(inode.next(), data, node.n())-1;
                    if( k<0 ) throw new NoSuchElementException();
                    return find(inode.next(k), data);
                }else
                    return find(inode.next(0), data);
            }else{
                ImmutableLeafNode<T> lnode = (ImmutableLeafNode<T>)node;
                int k = search(lnode.keys(), data, node.n());
                if( k<0 ) throw new NoSuchElementException();
                return new BPlusLocation<>(k, lnode);
            }
        }    
        throw new NoSuchElementException();
    }

    @Override
    public BPlusLocation<T> find(T data) {
        return find(root,data);
    }

    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	if( root != null )
    		sb.append(root.toString(0));
    	return sb.toString();
    }

	@Override
	public T get(BPlusLocation<T> loc){ return loc.get(); }

	@Override
	public Collection<KeyValue<BPlusLocation<T>, T>> pairs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(BPlusLocation<T> loc) { return loc!=null; }
}