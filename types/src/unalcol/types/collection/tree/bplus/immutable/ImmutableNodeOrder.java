/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus.immutable;

import unalcol.services.MicroService;
import unalcol.sort.Order;

/**
 *
 * @author jgomez
 */
public class ImmutableNodeOrder<T> extends MicroService<ImmutableNode<T>> implements Order<ImmutableNode<T>>{
    protected Order<T> inner;
    public ImmutableNodeOrder( Order<T> _inner ){
        inner = _inner;
    }
    @Override
    public int compare(ImmutableNode<T> a, ImmutableNode<T> b){
        return inner.compare(a.leftKey(), b.leftKey());
    }

}
