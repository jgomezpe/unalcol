package unalcol.collection.keymap;

import unalcol.clone.Clone;

public class KeyValueClone<K,V> implements Clone {
	public KeyValue<K,V> clone( KeyValue<K,V> kv ){ return new KeyValue<K, V>(kv.key(), kv.value());	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone(Object obj){ return clone((KeyValue<K,V>)obj); }
}