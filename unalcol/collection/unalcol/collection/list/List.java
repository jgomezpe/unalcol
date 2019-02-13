package unalcol.collection.list;
import java.util.Iterator;

import unalcol.collection.Collection;
import unalcol.collection.Finite;
import unalcol.collection.Mutable;
import unalcol.collection.Search;
import unalcol.collection.keymap.KeyValue;

/**
 * <p>Title: List</p>
 *
 * <p>Description: A list of objects</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class List<T> implements Finite<T>, Mutable<Node<T>, T>, Search<Node<T>, T> {
    protected int size = 0;
    protected Node<T> head = null;
    protected Node<T> last = null;

    public List(){}

    /**
     * Removes all the objects in the data structure
     */
    @Override    
    public void clear(){
        head = null;
        last = null;
        size = 0;
    }

    /**
     * Determines the number of objects stored by the data structure
     * @return Number of objects stored by the data structure
     */
    @Override
    public int size(){ return size; }

    /**
     * Determines if the data structure is empty or not
     * @return <i>true</i> if the data structure is empty <i>false</i> otherwise
     */
    @Override
    public boolean isEmpty(){
        return (size==0);
    }

    /**
     * Obtains an iterator of the objects in the list
     * @return Iterator of the objects in the list
     */
    @Override
    public Iterator<T> iterator(){
        return new ListIterator<T>( head );
    }


    /**
     * Inserts a data element in the structure
     * @param data Data element to be inserted
     * @return <i>true</i> if the element could be added, <i>false</i> otherwise
     */
    @Override
    public boolean add(T data) {
        Node<T> aux = new Node<>(data);
        if (head == null) {
            head = aux;
        } else {
            last.next = aux;
        }
        last = aux;
        size++;
        return true;
    }

    protected boolean del( Node<T> node ){
        boolean flag = (node != null);
        if( flag ){
            size--;
            if( node.prev != null ){
                node.prev.next = node.next;
            }else{
                head = node.next;
            }
            if( node.next != null ){
                node.next.prev = node.prev;
            }else{
                last = node.prev;
            }
        }
        return flag;
    }

    /**
     * Removes the element indicated by the locator
     * @param locator The location information of the object to be deleted
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
	@Override
    public boolean remove( Node<T> locator ){ return del( locator ); }

    /**
     * Removes a data element from the structure
     * @param data Data element to be removed
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    @Override
    public boolean del(T data){
        Node<T> aux = head;
        while( aux != null && !data.equals(aux.data) ){
            aux = aux.next;
        }
        return del( aux );
    }

	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data object if the object belongs to the data structure, <i>false</i> otherwise
	 */
	@Override
	public Node<T> find( T data ){
		Node<T> aux = head;
		while( aux != null && !data.equals(aux.data) ){ aux = aux.next; }
		return aux;
	}

	public T get(){ return get(head); }

	@Override
	public boolean valid(Node<T> key){ return (key!=null); }	

	@Override
	public T get(Node<T> key){ return key.data; }	

	@Override
	public Collection<KeyValue<Node<T>, T>> pairs() {
		// TODO Auto-generated method stub
		return null;
	}
}