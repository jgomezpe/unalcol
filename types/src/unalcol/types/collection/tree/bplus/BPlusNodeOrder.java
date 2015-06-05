/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus;

import unalcol.sort.Order;

/**
 *
 * @author jgomez
 */
public class BPlusNodeOrder<T> extends Order<BPlusNode<T>>{
    protected Order<T> inner;
    public BPlusNodeOrder( Order<T> _inner ){
        inner = _inner;
    }
    @Override
    public int compare(BPlusNode<T> a, BPlusNode<T> b){
        return inner.compare(a.leftKey(), b.leftKey());
    }

}
