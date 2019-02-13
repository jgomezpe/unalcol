package unalcol.iterator;

import java.util.Iterator;

public interface Backable<T> extends Iterator<T>{

	/**
	 * Determines the maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 * @return Maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 */
	public int maxBack() ;

	/**
	 * Returns the last k read character to the stream, if possible
	 * @param k Number of characters to be returned to the stream
     * @return true if it was possible to return the last k read character, false otherwise
     */
	public boolean back(int k);
	
	/**
	 * Returns the last read character to the stream, if possible
	 * @return true if it was possible to return the last read character, false otherwise
	 */
	public default boolean back(){ return back(1); }
	
	/**
	 * Resets the reader to the previous mark. Marks are dynamically adjusted in such a way that
	 * if the number of characters read, after calling the mark method, is greater than the size of the buffer
	 * then the mark is moved to such maximum number of characters. In this way at least the last n characters
	 * are always maintained by the reader.
	 */
	public default void reset(){ back(maxBack()); }
	
}