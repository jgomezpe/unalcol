/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection.tree.bplus.immutable;

import unalcol.sort.Order;

/**
 *
 * @author jgomez
 */
public class ImmutableNodeOrder<T> implements Order{
    protected Order inner;
    public ImmutableNodeOrder( Order _inner ){ inner = _inner; }
    
    public int compare(ImmutableNode<T> a, ImmutableNode<T> b){ return inner.compare(a.leftKey(), b.leftKey()); }

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object a, Object b){ return compare((ImmutableNode<T>)a, (ImmutableNode<T>)b); }
}