package unalcol.collection.keymap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.collection.Collection;
import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;
import unalcol.collection.keymap.KeyValue;
import unalcol.exception.ParamsException;
import unalcol.collection.keymap.Key;

public class MultiKeyCollection<T>  implements KeyMap<String, T>{
	protected MultiKey<T> key;
	protected HashMap<String, String> metaId = new HashMap<String,String>();
	protected HashMap<String,T> elements = new HashMap<String,T>();
	
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
	public T get( String id ) throws ParamsException{
		String meta = metaId.get(id);
		return elements.get(meta);
	}
	
	public Collection<String> keys(){ return metaId.keys(); }

	@Override
	public boolean isEmpty(){ return elements.isEmpty(); }

	@Override
	public Iterator<T> iterator(){ return elements.iterator(); }

	@Override
	public String find(T data) throws ParamsException{ return elements.find(data); }

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
					public KeyValue<String, T> next()  throws NoSuchElementException{
						try{
							String id = iter.next();
							T element = elements.get(metaId.get(id));
							return new KeyValue<String, T>(id, element);
						}catch(Exception e){ throw new NoSuchElementException( e.getMessage() ); }	
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
		try{
			String id = metaId.get(key);
			remove(  id.split(",") );
			return elements.remove(id);
		}catch(Exception e){ return false; }
	}

	@Override
	public boolean set(String key, T value) {
		String id;
		try{ id = metaId.get(key); }
		catch( Exception e ){
			id = key;
			metaId.set(key,key);
		}
		elements.set(id,value);
		return true;
	}
}