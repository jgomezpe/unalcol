package unalcol.types.collection.sparse.vector;
import unalcol.sort.Order;

/**
 * <p>Title: SparseValueOrder</p>
 * <p>Description: Compares two SparseValue.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */


public class SparseElementOrder<T> extends Order<SparseElement<T>> {

    /**
     * Determines if the object is less than (in some order) the given object
     * @param x Object to Compare
     * @param y Object to Compare
     * @return true if the object is less than the given object x. false in other case
     */
    @Override
    public int compare(SparseElement<T> x, SparseElement<T> y) {
        return (x.index() - y.index());
    }
}
