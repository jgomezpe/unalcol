package unalcol.collection.list;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Title: ListIterator</p>
 *
 * <p>Description: A List Iterator</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
class ListIterator<T> implements Iterator<T>{

    protected Node<T> node;

    public ListIterator( Node<T> node ){
        this.node = new Node<>();
        this.node.next = node;
    }

    @Override
    public boolean hasNext(){
        return (node.next!=null);
    }

    @Override
    public T next()  throws NoSuchElementException{
        try{
            node = node.next;
            return node.data;
        }catch( Exception e ){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        if( node != null ){
            if( node.prev != null ){
                node.prev.next = node.next;
            }
            if( node.next != null ){
                node.next.prev = node.prev;
            }
        }    
    }    
}