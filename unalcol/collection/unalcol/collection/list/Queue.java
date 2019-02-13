package unalcol.collection.list;
import java.util.NoSuchElementException;

/**
 * <p>Title: Queue</p>
 *
 * <p>Description: A queue </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Queue<T> extends List<T>{
    public Queue() {
    }

    public boolean del( T data ){
        try{
            del();
            return true;
        }catch( NoSuchElementException e ){
            return false;
        }
    }

    public void del() throws NoSuchElementException{
        try{
            head = head.next;
            size--;
            if (head == null) {
                last = null;
            }
        }catch( Exception e ){
            throw new NoSuchElementException();
        }
    }
}
