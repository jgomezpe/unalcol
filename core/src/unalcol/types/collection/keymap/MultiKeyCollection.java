package unalcol.types.collection.keymap;

import java.util.Iterator;

import unalcol.types.collection.Collection;

public class MultiKeyCollection<T>  implements KeyMap<String, T>{
	protected MultiKey<T> key;
	protected HTKeyMap<String, String> metaId = new HTKeyMap<String,String>();
	protected HTKeyMap<String,T> elements = new HTKeyMap<String,T>();
	
	public MultiKeyCollection( MultiKey<T> key ) { this.key = key; }
	public MultiKeyCollection( Key<String, T> key ) { this( new SplitMultiKey<T>(key) ); }
	
	protected String merge( String[] keys ){
		StringBuilder sb = new StringBuilder();
		sb.append(keys[0]);
		for( int i=1; i<keys.length; i++ ){
			sb.append(',');
			sb.append(keys[i]);
		}
		return sb.toString();
	}
	
	@Override
	public boolean del( T element ){
		String[] keys = key.keys(element);
		remove(keys);
		return elements.remove(merge(keys));
	}
	
	@Override
	public boolean add( T element ){
		String[] keys = key.keys(element);
		if( keys==null || keys.length==0 ) return false;
		for(String key:keys) if( metaId.contains(key) ) return false;
		String id = merge(keys);
		for(String key:keys) metaId.set(key, id);
		return elements.set(id,element); 
	}
	
	@Override
	public T get( String id ){
		String meta = metaId.get(id);
		if( meta == null ) return null;
		return elements.get(meta);
	}
	
	public Collection<String> keys(){ return metaId.keys(); }

	@Override
	public boolean isEmpty(){ return elements.isEmpty(); }

	@Override
	public Iterator<T> iterator(){ return elements.iterator(); }

	@Override
	public String find(T data){ return elements.find(data); }

	@Override
	public boolean valid(String key){ return metaId.valid(key); }

	@Override
	public Collection<KeyValue<String, T>> pairs() {
		return new Collection<KeyValue<String,T>>(){
			
			@Override
			public Iterator<KeyValue<String, T>> iterator() {
				return new Iterator<KeyValue<String,T>>() {
					protected Iterator<String> iter = metaId.keys().iterator();
					@Override
					public boolean hasNext(){ return iter.hasNext(); }

					@Override
					public KeyValue<String, T> next() {
						String id = iter.next();
						T element = elements.get(metaId.get(id));
						return new KeyValue<String, T>(id, element);
					}
				};
			}

			@Override
			public boolean isEmpty(){ return elements.isEmpty(); }
			
		};
	}

	@Override
	public void clear() {
		metaId.clear();
		elements.clear();
	}

	@Override
	public int size() {
		return 0;
	}

	protected void remove(String[] keys){ for( String s:keys ) metaId.remove(s); }
	
	@Override
	public boolean remove(String key) {
		String id = metaId.get(key);
		if( id == null ) return false;
		remove(  id.split(",") );
		return elements.remove(id);
	}

	@Override
	public boolean set(String key, T value) {
		String id = metaId.get(key);
		if( id == null ){
			id = key;
			metaId.set(key,key);
		}
		elements.set(id,value);
		return true;
	}
}