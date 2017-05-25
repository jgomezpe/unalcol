package unalcol.learn;

/**
 * <p>Title: Prediction</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez 
 * @version 1.0
 *
 */
public interface Prediction<T> {
     public T label();
    public double confidence();
}
