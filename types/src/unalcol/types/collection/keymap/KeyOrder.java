package unalcol.types.collection.keymap;

import unalcol.sort.Order;

public class KeyOrder<K,V> extends Order<KeyValue<K,V>>{
	protected Order<K> keys_order;
	public KeyOrder( Order<K> keys_order ){
		this.keys_order = keys_order;
	}
	@Override
	public int compare(KeyValue<K,V> x, KeyValue<K,V> y) {
		return keys_order.compare(x.key, y.key);
	}
}