/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection;
import java.util.Iterator;

/**
 *
 * @author jgomez
 */
public interface SearchCollection<T> extends Collection<T> {
    /**
        * Locates the given object in the structure
        * @param data Data object to be located
        * @return A data iterator starting at the given object (when the next method is called),
        * If the element is not in the data structure the get method will return an exception
        */
    public Location<T> find(T data);

    /**
        * Determines if the given object belongs to the structure
        * @param data Data object to be located
        * @return <i>true</i>If the objects belongs to the structure, <i>false>otherwise</i>
        */
    public boolean contains( T data );   
    
    /**
        * Obtains an iterator of the objects in the structure starting at the given Locator
        * @param locator Locator used for starting the iterator, the first element returned by the iterator is the one in the locator
        * (if some one is in the locator)
        * @return Iterator of the objects in the structure starting at the given Locator
        */
    public Iterator<T> iterator( Location<T> locator );
    
}
