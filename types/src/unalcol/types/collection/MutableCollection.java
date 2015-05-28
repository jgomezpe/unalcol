package unalcol.types.collection;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface MutableCollection<T> extends Collection<T>{
        /**
         * Removes all the objects in the data structure
         */
        public void clear();

        /**
         * Inserts a data element in the structure
         * @param data Data element to be inserted
         * @return <i>true</i> if the element could be added, <i>false</i> otherwise
         */
        public boolean add(T data);

        /**
         * Removes a data element from the structure
         * @param data Data element to be removed
         * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
         */
        public boolean del(T data);

        /**
         * Removes the next element returned by the iterator
         * @param locator Location of the object to be deleted in the structure
         * @return <i>true</i> if the next element returned by the iterator could be removed, <i>false</i> otherwise
         */
        public boolean del( Location<T> locator );

}
