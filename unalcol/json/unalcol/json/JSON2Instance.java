package unalcol.json;

public interface JSON2Instance<T>{
	public T load( JSON json );
	public JSON store( T obj );
}