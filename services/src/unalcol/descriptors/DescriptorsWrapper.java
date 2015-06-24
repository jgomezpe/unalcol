package unalcol.descriptors;

import java.lang.reflect.Method;

/**
 * <p>Descriptors wrapper method. Used for classes that already define a descriptors method</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class DescriptorsWrapper extends Descriptors<Object> {
    protected String method_name = "descriptors";
    /**
     * Obtains the descriptors of an object
     * @param obj Object to be analyzed
     * @return An array of double values used for describing the object
     */
    @Override
    public double[] descriptors(Object obj) {
        try {
            Method m = obj.getClass().getMethod(method_name) ;
            return (double[]) m.invoke(obj);
        } catch (Exception e) {
        }
        return null;
    }
}