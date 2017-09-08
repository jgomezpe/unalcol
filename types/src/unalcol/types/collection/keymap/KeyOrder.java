package unalcol.types.collection.keymap;
import unalcol.services.MicroService;
import unalcol.sort.Order;

public class KeyOrder<K,V> extends MicroService<KeyValue<K,V>> implements Order<KeyValue<K,V>>{
	protected Order<K> order;
	public KeyOrder( Order<K> keys_order ){
		this.order = keys_order;
	}
	
	@Override
	public int compare(KeyValue<K,V> x, KeyValue<K,V> y) {
		return order.compare(x.key, y.key);
	}
}