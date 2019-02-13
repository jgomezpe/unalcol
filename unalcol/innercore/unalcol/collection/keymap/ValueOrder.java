package unalcol.collection.keymap;

import unalcol.sort.Order;

public class ValueOrder<K,V> implements Order{
	protected Order order;
	public ValueOrder( Order values_order ){ this.order = values_order; }
	
	public int compare(KeyValue<K,V> x, KeyValue<K,V> y){ return order.compare(x.value, y.value); }

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object x, Object y) { return compare((KeyValue<K,V>)x, (KeyValue<K,V>)y); }
}