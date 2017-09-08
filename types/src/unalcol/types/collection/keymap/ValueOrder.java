package unalcol.types.collection.keymap;

import unalcol.services.MicroService;
import unalcol.sort.Order;

public class ValueOrder<K,V> extends MicroService<KeyValue<K,V>> implements Order<KeyValue<K,V>>{
	protected Order<V> order;
	public ValueOrder( Order<V> values_order ){
		this.order = values_order;
	}
	
	@Override
	public int compare(KeyValue<K,V> x, KeyValue<K,V> y) {
		return order.compare(x.value, y.value);
	}
}