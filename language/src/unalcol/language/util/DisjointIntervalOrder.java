package unalcol.language.util;
import unalcol.sort.Order;

/**
 * <p>Title: DisjointIntervalOrder</p>
 *
 * <p>Description: An order for disjoint intervals</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class DisjointIntervalOrder implements Order<Interval>{
    public String getCanonicalName(){
        return Interval.class.getCanonicalName();
    }

    /**
     * Determines if interval one is less than (as disjoint intervals) interval two
     * @param one The first interval to compare
     * @param two The secont interval to compare
     * @return (inf(one)<inf(two))
     */
    public int compare(Interval one, Interval two) {
        return (one.inf - two.inf);
    }
}
