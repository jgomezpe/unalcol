package unalcol.types.collection;

import java.util.Iterator;

public abstract class ShortTermMemoryIterator<K,T> implements UnalcolIterator<K,T>{
	
	protected Iterator<T> iter;
	
	/**
	 * Default number of characters that is able to maintain the reader (last read characters)
	 */
	public static int MEMORY_SIZE = 100000;

	/**
	 * Last read characters
	 */
	protected T[] data = null;
	protected K[] extra = null;
	/**
	 * Number of characters that is able to maintain the reader (last read characters)
	 */
	protected int n;

	protected int offset=0;
	protected int start = 0;
	protected int end = 0;
	/**
	 * Current cursor position
	 */
	protected int pos = 0;

	public ShortTermMemoryIterator( Iterator<T> iter ) { this( iter, MEMORY_SIZE ); }
	
	@SuppressWarnings("unchecked")
	public ShortTermMemoryIterator( Iterator<T> iter , int n ){
		this.n = n;
		this.iter = iter;
		data = (T[])new Object[n];
		extra = (K[])new Object[n];
	}
	
	protected abstract K key(T data);
	
	public K key(){ return extra[pos]; }
	
	@Override
	public boolean hasNext() { return (pos!=end || iter.hasNext()); }

	@Override
	public T next(){
		T c;
		if (pos == end) {
			c = iter.next();
			end = (end + 1 < n) ? end + 1 : 0;
			pos = end;
			data[pos] = c;
			extra[pos] = key(c);
			if (end == start) start = (start + 1 < n) ? start + 1 : 0;
		}else{
			pos = (pos + 1 < n) ? pos + 1 : 0;
			c = data[pos];
		}	
		return c;
	}

	/**
	 * Determines the maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 * @return Maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 */
	public int maxBack() {
		if (pos >= start)return pos - start;
		return n + pos - start;
	}

	/**
	 * Returns the last k read character to the stream, if possible
	 * @param k Number of characters to be returned to the stream
     * @return true if it was possible to return the last k read character, false otherwise
     */
	public boolean back(int k) {
		boolean flag = (0<k && k <= maxBack());
		if (flag) {
			offset -= k;
			pos -= k;
			if (pos < 0) {
				pos += n;
			}
		}
		return flag;
	}
	
	/**
	 * Returns the last read character to the stream, if possible
	 * @return true if it was possible to return the last read character, false otherwise
	 */
	/*public boolean back() {
		boolean flag = (pos != start);
		if (flag) {
			offset--;
			pos--;
			pos = (pos < 0) ? n - 1 : pos;
       	}
		return flag;
	}*/
	
	/**
	 * Marks the actual position as a mark for reseting the reader
	 */
	public void mark() { start = pos; }
}