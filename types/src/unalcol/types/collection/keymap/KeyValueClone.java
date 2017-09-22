package unalcol.types.collection.keymap;

import unalcol.clone.Clone;
import unalcol.services.MicroService;

public class KeyValueClone<K,V> extends MicroService<KeyValue<K,V>> implements Clone<KeyValue<K,V>> {
	@Override
	public KeyValue<K,V> clone(){
		KeyValue<K,V> kv = caller(); 
		return new KeyValue<K, V>(kv.key(), kv.value());
	}
}