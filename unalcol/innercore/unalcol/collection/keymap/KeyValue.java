package unalcol.collection.keymap;

public class KeyValue<K,V> {
	protected K key;
	protected V value;

	public KeyValue(K key, V value){
		this.value = value;
		this.key = key;
	}

	public K key(){ return key; }
	public V value(){ return value; }

	public void setKey( K key ){ this.key = key; }
	public void setValue( V value ){ this.value = value; }

	public String toString(){ return key.toString()+":"+value.toString(); }
}