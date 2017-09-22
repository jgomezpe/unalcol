package unalcol.types.collection.array;
import unalcol.types.collection.keymap.KeyMap;
/**
 * <p>Title: MutableArrayCollection</p>
 *
 * <p>Description: An array collection that can be modified</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Array<T> extends ImmutableArray<T>, KeyMap<Integer,T>{
	/**
	 * Adds an element to the array at the given position
	 * @param index Position where the element will be located in the array
	 * @param data Element to be added
	 * @return <i>true</i> if the element was added, <i>false</i> otherwise
	 * @throws ArrayIndexOutOfBoundsException if the given index is out of the array range <i> 0 &lt;= index &lt;=size</i>
	 */
	public boolean add( int index, T data )  throws ArrayIndexOutOfBoundsException;
	
	/**
	 * Sets an element at the given position in the array
	 * @param index Position where the element will be set in the array
	 * @return <i>true</i> if the element was set, <i>false</i> otherwise
	 * @throws ArrayIndexOutOfBoundsException if the given index is out of the array range <i> 0 &lt;= index &lt;=size</i>
	 */
	public boolean set( int index, T data ) throws ArrayIndexOutOfBoundsException;

	/**
	 * Removes the element of the array at the given position
	 * @param index Position where the element will be removed from the array
	 * @return <i>true</i> if the element was removed, <i>false</i> otherwise
	 * @throws ArrayIndexOutOfBoundsException if the given index is out of the array range <i> 0 &lt;= index &lt;=size</i>
	 */
	public boolean remove( int index ) throws ArrayIndexOutOfBoundsException;
	
	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize( int n );
	
	public default boolean add( Integer key, T value ){ return add((int)key,value); }
	public default boolean set( Integer key, T value ){ return set((int)key,value); }
	public default boolean remove( Integer key ){ return remove((int)key); }	
}