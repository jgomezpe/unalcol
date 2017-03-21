package unalcol.types.collection.keymap;

import unalcol.sort.Order;

public class ValueOrder<K,V> extends Order<KeyValue<K,V>>{
	protected Order<V> values_order;
	public ValueOrder( Order<V> values_order ){
		this.values_order = values_order;
	}
	@Override
	public int compare(KeyValue<K,V> x, KeyValue<K,V> y) {
		return values_order.compare(x.value, y.value);
	}
}