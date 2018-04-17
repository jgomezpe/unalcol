package unalcol.types.collection.keymap;

import java.util.Iterator;

import unalcol.types.collection.Collection;
import unalcol.types.collection.GrowCollection;
import unalcol.types.collection.SearchCollection;

public class MultiIdCollection<T>  implements SearchCollection<String, T>, GrowCollection<T>{
	protected Key<String,T> key;
	protected HTKeyMap<String, String> metaId = new HTKeyMap<String,String>();
	protected HTKeyMap<String,T> elements = new HTKeyMap<String,T>();
	
	public MultiIdCollection( Key<String,T> key ) { this.key = key; }
	
	public boolean add( T element ){
		String id = key.get(element);;
		if( id==null ) return false;
		String[] k = id.split(",");
		if( k==null ) return false;
		for(String s:k) if( metaId.contains(s) ) return false;
		for(String s:k) metaId.set(s, id);
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
}
