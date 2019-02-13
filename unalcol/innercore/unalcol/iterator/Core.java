package unalcol.iterator;

import java.util.Iterator;

public abstract class Core<T> implements BT<T>{
	
	protected Iterator<T> iter;
	
	/**
	 * Default number of characters that is able to maintain the reader (last read characters)
	 */
	public static int MEMORY_SIZE = 100000;

	/**
	 * Last read characters
	 */
	protected Object[] data = null;
	protected PositionTrack[] extra = null;
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

	public Core( Iterator<T> iter ) { this( iter, MEMORY_SIZE ); }
	
	public Core( Iterator<T> iter , int n ){
		this.n = n;
		this.iter = iter;
		data = new Object[n];
		extra = new PositionTrack[n];
	}
	
	protected abstract PositionTrack pos(T data);
	
	public PositionTrack pos(){ return extra[pos]; }
	
	@Override
	public boolean hasNext() { return (pos!=end || iter.hasNext()); }

	@SuppressWarnings("unchecked")
	@Override
	public T next(){
		T c;
		if (pos == end) {
			c = iter.next();
			end = (end + 1 < n) ? end + 1 : 0;
			pos = end;
			data[pos] = c;
			extra[pos] = pos(c);
			if (end == start) start = (start + 1 < n) ? start + 1 : 0;
		}else{
			pos = (pos + 1 < n) ? pos + 1 : 0;
			c = (T)data[pos];
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
	 * Marks the actual position as a mark for reseting the reader
	 */
	public void mark() { start = pos; }
}