package unalcol.data.file;

public interface Load<T> {
	public T load( byte[] buffer );
	public byte[] store( T obj );
	public int size();
}
