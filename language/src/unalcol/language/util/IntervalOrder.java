package unalcol.language.util;
import unalcol.sort.*;


/**
 * <p>Title: IntervalOrder</p>
 *
 * <p>Description: A lexicographical order mechanism for Interval objects.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacionnal de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class IntervalOrder implements Order<Interval>{
    public String getCanonicalName(){
        return Interval.class.getCanonicalName();
    }

    /**
     * Determines if interval one is less than (lexicographical order) interval two
     * @param one The first interval to compare
     * @param two The secont interval to compare
     * @return (inf(one)<inf(two) || (inf(one)=inf(two) && supe(one)<sup(two)))
     */
    public int compare(Interval one, Interval two){
        int dif = one.inf-two.inf;
        return (dif==0)?one.sup-two.sup:dif;
    }
}
