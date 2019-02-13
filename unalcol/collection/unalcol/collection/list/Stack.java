package unalcol.collection.list;

/**
 * <p>Title: Stack</p>
 *
 * <p>Description: An stack </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Stack<T> extends Queue<T>{
    public Stack() {
    }

    public boolean add( T data ){
        Node<T> node = new Node<T>(data);
        node.next = head;
        if( head == null ){ last = node; }
        head = node;
        size++;
        return true;
    }

    public void push( T data ){ add( data ); }

    public T pop(){
		T t = head.data;
		del();
		return t;
    }
}
