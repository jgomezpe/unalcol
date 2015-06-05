package unalcol.sort;
import java.lang.reflect.Method;

/**
 * <p>An order wrappper method. Used for classes that already define a compareTo method</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class OrderWrapper extends Order<Object>{
    /**
     * Creates an order wrapped method for classes with the compareTo method
     */
    protected String method_name = "compareTo";
    

    /**
     * Determines if one elements is less, equal or greater than other.
     * A value < 0 indicates that one is less than two, a value = 0 indicates
     * that one is equal to two and a value > 0 indicates that one is greater than two
     * @param one First object to be compared
     * @param two Second object to be compared
     * @return a value < 0 if one < two, 0 if one == two and > 0 if one > two.
     */
    public int compare( Object one, Object two ){
        try{
           Method m = one.getClass().getMethod(method_name, two.getClass()) ;
           return (Integer)m.invoke(one, two);
        }catch( Exception e ){
            return (one.toString().compareTo(two.toString()));
        }
    }
}