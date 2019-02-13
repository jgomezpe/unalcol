package unalcol.collection.keymap;

import unalcol.sort.Order;

public class KeyOrder<K,V>  implements Order{
	protected Order order;
	
	public KeyOrder( Order keys_order ){ this.order = keys_order; }
	
	public int compare(KeyValue<K,V> x, KeyValue<K,V> y){ return order.compare(x.key, y.key); }

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object x, Object y){ return compare((KeyValue<K,V>)x, (KeyValue<K,V>)y); }
}