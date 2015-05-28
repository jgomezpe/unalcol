package unalcol.types.collection.list;
import unalcol.types.collection.Location;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * <p>Title: ListLocation</p>
 *
 * <p>Description: A class for locating objects in a list</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
class ListLocation<T> implements Location<T>{

    protected Node<T> node;

    public ListLocation( Node<T> node ){
        this.node = node;
    }

    public Iterator<T> iterator(){
        return new ListIterator<T>(node);
    }

    public T get()throws NoSuchElementException{
        if( node == null ) throw new NoSuchElementException();
        return node.data;
    }
}
