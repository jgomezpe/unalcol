package unalcol.learn.unsupervised;


/**
 * <p>Title: Cluster</p>
 * <p>Description: This class represents a cluster in the n-dimensional real space.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public interface Cluster<T> {

	/**
	 * Member Ship
	 * @param obj Object
	 * @return Double
	 */
     public double membership( T obj );
}
