package unalcol.types.object;

import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

public class UsesKeyMap<K,V> {
	protected KeyMap<K, V> keymap;
	
	public UsesKeyMap( KeyMap<K, V> keymap ){ this.keymap = keymap; }
	
	public UsesKeyMap(){ this( new HTKeyMap<K,V>() ); }
}