package unalcol.collection;
import unalcol.collection.array.Immutable;
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
public interface Array<T> extends Immutable<T>, KeyMap<Integer,T>{	
	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize( int n );
	
	/**
	 * Adds an element to the array at the given position
	 * @param index Position where the element will be located in the array
	 * @param data Element to be added
	 * @return <i>true</i> if the element was added, <i>false</i> otherwise
	 * @throws ArrayIndexOutOfBoundsException if the given index is out of the array range <i> 0 &lt;= index &lt;=size</i>
	 */
	public boolean add( Integer key, T value );
}