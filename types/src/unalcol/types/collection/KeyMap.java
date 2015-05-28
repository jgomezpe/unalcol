/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection;

/**
 *
 * @author jgomez
 */
public interface KeyMap<K,T> {
    public T get( K key, Collection<T> collection );
    public K key( T obj, Collection<T> collection );
}
